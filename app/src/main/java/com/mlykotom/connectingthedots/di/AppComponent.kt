package com.mlykotom.connectingthedots.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.mlykotom.connectingthedots.ConnectingTheDotsApp
import com.mlykotom.connectingthedots.presentation.MainActivity
import com.mlykotom.connectingthedots.presentation.SomeFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AppCommonModule::class,
        CommonUiModule::class,
        MyUiBuilderModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            application: Application
        ): AppComponent
    }

    val context: Context
    val application: Application
    val sharedPrefs: SharedPreferences

    fun inject(connectingTheDotsApp: ConnectingTheDotsApp)
    fun inject(connectingTheDotsApp: MainActivity)
    fun inject(someFragment: SomeFragment)
}

