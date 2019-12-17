package com.mlykotom.connectingthedots

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.mlykotom.connectingthedots.di.AppComponent
import com.mlykotom.connectingthedots.di.DaggerAppComponent

class ConnectingTheDotsApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .factory()
            .create(this)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}

val Activity.appComponent get() = (application as ConnectingTheDotsApp).appComponent
val Fragment.appComponent get() = (requireActivity().application as ConnectingTheDotsApp).appComponent
