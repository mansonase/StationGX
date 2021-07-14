/*
    Copyright 2021 Picovoice Inc.

    You may not use this file except in compliance with the license. A copy of the license is
    located in the "LICENSE" file accompanying this source.

    Unless required by applicable law or agreed to in writing, software distributed under the
    License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
    express or implied. See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.corbit.stationgx.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.widget.Toast;

import com.corbit.stationgx.R;
import com.corbit.stationgx.pages.mainbaseactivity.MainBaseActivity;
import com.corbit.stationgx.pages.myprofile.MyProfileActivity;

import java.io.File;
import java.util.Map;

//import ai.picovoice.porcupine.Porcupine;
//import ai.picovoice.porcupine.PorcupineException;
//import ai.picovoice.porcupine.PorcupineManager;
//import ai.picovoice.rhino.RhinoInference;
//import ai.picovoice.rhino.RhinoManager;
import ai.picovoice.picovoice.PicovoiceException;
import ai.picovoice.picovoice.PicovoiceManager;
import ai.picovoice.porcupine.PorcupineException;
import ai.picovoice.porcupine.PorcupineManager;
import ai.picovoice.rhino.RhinoInference;
import ai.picovoice.rhino.RhinoManager;
import ai.picovoice.rhino.RhinoManagerCallback;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class PorcupineService extends Service {
    private static final String CHANNEL_ID = "PorcupineServiceChannel";

    private PorcupineManager porcupineManager;

    private RhinoManager rhinoManager;

    private PicovoiceManager picovoiceManager;

    private int numUtterances;

    private String[] ttsResponseArr;
    private TextToSpeech tts;
    private boolean startDetect = true;

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Porcupine",
                    NotificationManager.IMPORTANCE_HIGH);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.ttsResponseArr = getApplicationContext().getResources().getStringArray(R.array.wake_words_responses);
        this.initTTSEngine();
        createNotificationChannel();

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                new Intent(this, MainBaseActivity.class),
                0);

        numUtterances = 0;

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Wake word")
                .setContentText("Service running")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1234, notification);

//        try {
//            picovoiceManager = new PicovoiceManager.Builder()
//                    .setKeywordPath(getAbsolutePath("wake_words.ppn"))
//                    .setPorcupineSensitivity(0.7f)
//                    .setWakeWordCallback(
//                            () -> {
//                                PendingIntent contentIntent = PendingIntent.getActivity(
//                                        this,
//                                        0,
//                                        new Intent(this, MainBaseActivity.class),
//                                        0);
//
//                                Notification n = new NotificationCompat.Builder(this, CHANNEL_ID)
//                                        .setContentTitle("Picovoice")
//                                        .setContentText("Wake Word Detected ...")
//                                        .setSmallIcon(R.drawable.ic_launcher_background)
//                                        .setContentIntent(contentIntent)
//                                        .build();
//
//                                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                                assert notificationManager != null;
//                                notificationManager.notify(1234, n);
//
//                            })
//                    .setContextPath(getAbsolutePath("speech_to_intent.rhn"))
//                    .setRhinoSensitivity(0.25f)
//                    .setInferenceCallback(
//                            (inference) -> {
//                                PendingIntent contentIntent = PendingIntent.getActivity(
//                                        this,
//                                        0,
//                                        new Intent(this, MainBaseActivity.class),
//                                        0);
//
//
//                                StringBuilder builder = new StringBuilder();
//                                if (inference.getIsUnderstood()) {
//                                    builder.append(inference.getIntent()).append(" - ");
//                                    final Map<String, String> slots = inference.getSlots();
//                                    if (slots.size() > 0) {
//                                        for (String key : slots.keySet()) {
//                                            builder.append(key).append(" : ").append(slots.get(key)).append(" ");
//                                        }
//                                    }
//                                } else {
//                                    builder.append("Didn't understand the command.");
//                                }
//
//                                Notification n = new NotificationCompat.Builder(this, CHANNEL_ID)
//                                        .setContentTitle("Picovoice")
//                                        .setContentText(builder.toString())
//                                        .setSmallIcon(R.drawable.ic_launcher_background)
//                                        .setContentIntent(contentIntent)
//                                        .build();
//
//                                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                                assert notificationManager != null;
//                                notificationManager.notify(1234, n);
//
//                            }).build(getApplicationContext());
//            picovoiceManager.start();
//        } catch (PicovoiceException e) {
//            Log.d("de", e.toString());
//        }

        try {
            porcupineManager = new PorcupineManager.Builder()
//                    .setKeyword(Porcupine.BuiltInKeyword.COMPUTER)
                    .setKeywordPath(getAbsolutePath("wake_words.ppn"))

                    .setSensitivity(0.3f).build(
                            getApplicationContext(),
                            (keywordIndex) -> {
                                numUtterances++;

                                tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                                    @Override
                                    public void onStart(String utteranceId) {
                                    }

                                    @Override
                                    public void onDone(String utteranceId) {
                                        if (startDetect) {
                                            rhinoManager.process();
                                        }
                                    }

                                    @Override
                                    public void onError(String utteranceId) {
                                    }
                                });
                                tts.speak(ttsResponseArr[(int)(Math.random()*2)], TextToSpeech.QUEUE_FLUSH, null, "");
                                startDetect = true;
                                PendingIntent contentIntent = PendingIntent.getActivity(
                                        this,
                                        0,
                                        new Intent(this, MainBaseActivity.class),
                                        0);

//                                final String contentText = numUtterances == 1 ? " time!" : " times!";
//                                Notification n = new NotificationCompat.Builder(this, CHANNEL_ID)
//                                        .setContentTitle("Wake word")
//                                        .setContentText("Detected " + numUtterances + contentText)
//                                        .setSmallIcon(R.drawable.ic_launcher_background)
//                                        .setContentIntent(contentIntent)
//                                        .build();
//
//                                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                                assert notificationManager != null;
//                                notificationManager.notify(1234, n);

//                                initRhino();
                                Toast.makeText(getApplicationContext(), "Detected "+numUtterances+"times", Toast.LENGTH_LONG).show();
                                try {
                                    porcupineManager.stop();
                                }
                                catch (PorcupineException e) {
                                    Log.d("de", "porcupineManager.stop e: "+e.toString());
                                }
//                                rhinoManager.process();

                            });
            porcupineManager.start();

            rhinoManager = new RhinoManager.Builder()
//                .setContextPath(getAbsolutePath("rhino_context.rhn"))
                    .setContextPath(getAbsolutePath("speech_to_intent.rhn"))
                    .setSensitivity(0.25f)
                    .build(getApplicationContext(), new RhinoManagerCallback() {
                        @Override
                        public void invoke(final RhinoInference inference) {
                            Toast.makeText(PorcupineService.this, "Detect intent", Toast.LENGTH_LONG).show();
                            if (inference.getIsUnderstood()) {
                                tts.speak("OK", TextToSpeech.QUEUE_FLUSH, null, "");
                                parseIntentData(inference);
//                                if (inference.getSlots().size() > 0) {
//                                    Map<String, String> map = inference.getSlots();
//                                    for (String key : map.keySet()) {
//                                        Log.d("de", key + ": " + map.get(key));
//                                    }
//                                }
                            } else {
                                Log.d("de", "can't understand");
                                tts.speak("I don't understand what you mean.", TextToSpeech.QUEUE_FLUSH, null, "");
                            }
                            startDetect = false;
                            porcupineManager.start();
                        }
                    });
//            rhinoManager.process();

        } catch (PorcupineException e) {
            Log.d("de", e.toString());
        } catch (Exception e) {
            Log.d("de", "111, "+e.toString());
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void initTTSEngine() {
        if (tts == null) {
            this.tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {

                }
            });
        }
    }

    private void parseIntentData(final RhinoInference inference) {
        String intent = inference.getIntent();
        switch (intent) {
            case "GoToPage":
                goToTargetPage(inference.getSlots());
                break;
            case "HealthData":
                break;
            default:
                break;
        }
    }

    private void goToTargetPage(final Map<String, String> slots) {
        for (String key : slots.keySet()) {
            String targetPage = slots.get(key);
            Log.d("de", "targetPage: "+targetPage);
            switch (targetPage) {
                case "home page":
                    goHomePage();
                    break;
                case "health data":
                    goHealthDataPage();
                    break;
                default:
                    break;
            }
        }
    }

    private void goHomePage() {
        Intent intent = new Intent(getApplicationContext(), MainBaseActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goHealthDataPage() {
        Intent intent = new Intent(getApplicationContext(), MyProfileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goToInputDataPage(final Map<String, String> slots) {
        Intent intent = new Intent(this, MyProfileActivity.class);
        startActivity(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }

//        try {
//            picovoiceManager.stop();
//        }
//        catch (PicovoiceException e) {
//            Log.d("de", "onDestroy exception: "+e.toString());
//        }
        try {
            porcupineManager.stop();
            porcupineManager.delete();

            rhinoManager.delete();
        } catch (PorcupineException e) {
            Log.d("de", "onDestroy exception: " + e.toString());
        }

        super.onDestroy();
    }

    private String getAbsolutePath(String filename) {
        return new File(this.getFilesDir(), filename).getAbsolutePath();
    }
}
