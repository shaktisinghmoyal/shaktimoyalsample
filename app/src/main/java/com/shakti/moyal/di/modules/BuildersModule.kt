package com.shakti.moyal.di.modules

import com.shakti.moyal.ui.mainui.MainActivity
import com.shakti.moyal.ui.mainui.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule
{
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity() : MainActivity
}