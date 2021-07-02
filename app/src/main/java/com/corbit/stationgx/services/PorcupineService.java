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
import android.util.Log;

import com.corbit.stationgx.R;
import com.corbit.stationgx.pages.mainbaseactivity.MainBaseActivity;

import java.io.File;

import ai.picovoice.porcupine.Porcupine;
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

    private int numUtterances;

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

        try {
            porcupineManager = new PorcupineManager.Builder()
                    .setKeyword(Porcupine.BuiltInKeyword.COMPUTER)
                    .setSensitivity(0.7f).build(
                            getApplicationContext(),
                            (keywordIndex) -> {
                                numUtterances++;

                                PendingIntent contentIntent = PendingIntent.getActivity(
                                        this,
                                        0,
                                        new Intent(this, MainBaseActivity.class),
                                        0);

                                final String contentText = numUtterances == 1 ? " time!" : " times!";
                                Notification n = new NotificationCompat.Builder(this, CHANNEL_ID)
                                        .setContentTitle("Wake word")
                                        .setContentText("Detected " + numUtterances + contentText)
                                        .setSmallIcon(R.drawable.ic_launcher_background)
                                        .setContentIntent(contentIntent)
                                        .build();

                                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                assert notificationManager != null;
                                notificationManager.notify(1234, n);
//                                initRhino();
                                rhinoManager.process();
                            });
            porcupineManager.start();

            rhinoManager = new RhinoManager.Builder()
//                .setContextPath(getAbsolutePath("rhino_context.rhn"))
                    .setContextPath(getAbsolutePath("speech_to_intent.rhn"))
                    .setSensitivity(0.25f)
                    .build(getApplicationContext(), new RhinoManagerCallback() {
                        @Override
                        public void invoke(final RhinoInference inference) {
                            if (inference.getIsUnderstood()) {
                                Log.d("de", "isUnderstood");
                            } else {
                                Log.d("de", "can't understand");
                            }
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

    private void initRhino() {
        try {
            rhinoManager = new RhinoManager.Builder()
//                .setContextPath(getAbsolutePath("rhino_context.rhn"))
                    .setContextPath(getAbsolutePath("speech_to_intent.rhn"))
                    .setSensitivity(0.25f)
                    .build(getApplicationContext(), new RhinoManagerCallback() {
                        @Override
                        public void invoke(final RhinoInference inference) {
                            if (inference.getIsUnderstood()) {
                                Log.d("de", "isUnderstood");
                            } else {
                                Log.d("de", "can't understand");
                            }
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    recordButton.setEnabled(true);
//                                    recordButton.setText("START");
//                                    recordButton.toggle();
//                                    intentTextView.setText("\n    {\n");
//                                    intentTextView.append(String.format("        \"isUnderstood\" : \"%b\",\n", inference.getIsUnderstood()));
//                                    if (inference.getIsUnderstood()) {
//                                        intentTextView.append(String.format("        \"intent\" : \"%s\",\n", inference.getIntent()));
//                                        final Map<String, String> slots = inference.getSlots();
//                                        if (slots.size() > 0) {
//                                            intentTextView.append("        \"slots\" : {\n");
//                                            for (String key : slots.keySet()) {
//                                                intentTextView.append(String.format("            \"%s\" : \"%s\",\n", key, slots.get(key)));
//                                            }
//                                            intentTextView.append("        }\n");
//                                        }
//                                    }
//                                    intentTextView.append("    }\n");
//                                }
//                            });
                        }
                    });
            rhinoManager.process();
        }
        catch (Exception e) {
            Log.d("de", "initRhino exception: "+e.toString());

        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        try {
            porcupineManager.stop();
            porcupineManager.delete();
        } catch (PorcupineException e) {
            Log.d("de", "onDestroy exception: " + e.toString());
        }

        super.onDestroy();
    }

    private String getAbsolutePath(String filename) {
        return new File(this.getFilesDir(), filename).getAbsolutePath();
    }
}
