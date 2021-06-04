package com.corbit.stationgx.application

import com.corbit.stationgx.pages.healthdata.HealthDataActivity
import com.corbit.stationgx.pages.healthdata.HealthDataActivityModule
import com.corbit.stationgx.pages.mainbaseactivity.MainBaseActivity
import com.corbit.stationgx.pages.mainbaseactivity.MainBaseActivityModule
import com.corbit.stationgx.pages.mainbaseactivity.MainBaseFragmentProvider
import com.corbit.stationgx.pages.manuelinput.ManualInputActivity
import com.corbit.stationgx.pages.manuelinput.ManualInputActivityModule
import com.corbit.stationgx.pages.manuelinput.bloodpressure.view.BloodPressureActivity
import com.corbit.stationgx.pages.manuelinput.weight.WeightActivity
import com.corbit.stationgx.pages.measurement.MeasurementActivity
import com.corbit.stationgx.pages.measurement.MeasurementActivityModule
import com.corbit.stationgx.pages.medication.MedicationActivity
import com.corbit.stationgx.pages.medication.MedicationActivityModule
import com.corbit.stationgx.pages.myprofile.MyProfileActivity
import com.corbit.stationgx.pages.myprofile.MyProfileActivityModule
import com.corbit.stationgx.pages.phone.PhoneActivity
import com.corbit.stationgx.pages.phone.PhoneActivityModule
import com.corbit.stationgx.pages.settings.SettingsActivity
import com.corbit.stationgx.pages.settings.SettingsActivityModule
import com.corbit.stationgx.pages.telehealth.TeleHealthActivity
import com.corbit.stationgx.pages.telehealth.TeleHealthActivityModule
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
    abstract fun bindBloodPressureActivity(): BloodPressureActivity

    @ContributesAndroidInjector()
    abstract fun bindWeightActivity():WeightActivity
}