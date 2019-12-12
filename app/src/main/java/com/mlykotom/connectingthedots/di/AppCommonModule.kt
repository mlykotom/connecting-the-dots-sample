package com.mlykotom.connectingthedots.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides

@Module
object AppCommonModule {
    @Provides
    fun provideAppContext(application: Application): Context = application.applicationContext

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
}