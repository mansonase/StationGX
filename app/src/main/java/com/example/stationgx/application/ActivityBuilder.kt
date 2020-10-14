package com.example.stationgx.application

import com.example.stationgx.pages.healthdata.HealthDataActivity
import com.example.stationgx.pages.healthdata.HealthDataActivityModule
import com.example.stationgx.pages.mainbaseactivity.homefragment.HomeActivity
import com.example.stationgx.pages.mainbaseactivity.homefragment.HomeActivityModule
import com.example.stationgx.pages.mainbaseactivity.MainBaseActivity
import com.example.stationgx.pages.mainbaseactivity.MainBaseActivityModule
import com.example.stationgx.pages.mainbaseactivity.MainBaseFragmentProvider
import com.example.stationgx.pages.manuelinput.ManualInputActivity
import com.example.stationgx.pages.manuelinput.ManualInputActivityModule
import com.example.stationgx.pages.manuelinput.bloodpressure.BloodPressureActivity
import com.example.stationgx.pages.measurement.MeasurementActivity
import com.example.stationgx.pages.measurement.MeasurementActivityModule
import com.example.stationgx.pages.medication.MedicationActivity
import com.example.stationgx.pages.medication.MedicationActivityModule
import com.example.stationgx.pages.myprofile.MyProfileActivity
import com.example.stationgx.pages.myprofile.MyProfileActivityModule
import com.example.stationgx.pages.phone.PhoneActivity
import com.example.stationgx.pages.phone.PhoneActivityModule
import com.example.stationgx.pages.settings.SettingsActivity
import com.example.stationgx.pages.settings.SettingsActivityModule
import com.example.stationgx.pages.telehealth.TeleHealthActivity
import com.example.stationgx.pages.telehealth.TeleHealthActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = [MainBaseActivityModule::class,MainBaseFragmentProvider::class])
    abstract fun binMainBaseActivity():MainBaseActivity

    @ContributesAndroidInjector(modules = [HealthDataActivityModule::class])
    abstract fun bindHealthDataActivity():HealthDataActivity

    @ContributesAndroidInjector(modules = [ManualInputActivityModule::class])
    abstract fun bindManualInputActivity():ManualInputActivity

    @ContributesAndroidInjector(modules = [MeasurementActivityModule::class])
    abstract fun bindMeasurementActivity():MeasurementActivity

    @ContributesAndroidInjector(modules = [MedicationActivityModule::class])
    abstract fun bindMedicationActivity():MedicationActivity

    @ContributesAndroidInjector(modules = [PhoneActivityModule::class])
    abstract fun bindPhoneActivity():PhoneActivity

    @ContributesAndroidInjector(modules = [TeleHealthActivityModule::class])
    abstract fun bindTeleHealthActivity():TeleHealthActivity

    @ContributesAndroidInjector(modules = [SettingsActivityModule::class])
    abstract fun bindSettingsActivity():SettingsActivity

    @ContributesAndroidInjector(modules = [MyProfileActivityModule::class])
    abstract fun bindMyProfileActivity():MyProfileActivity

    @ContributesAndroidInjector()
    abstract fun bindBloodPressureActivity():BloodPressureActivity
}