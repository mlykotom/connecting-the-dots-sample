package com.mlykotom.connectingthedots.presentation

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import com.mlykotom.connectingthedots.R
import javax.inject.Inject

/**
 * Pure Dagger ViewModel without AssistedInject
 */
class MainViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val appContext: Context
) : ViewModel() {

    init {
        // if wouldn't be injected properly, it would crash
        Log.d(
            "MainViewModel",
            "Application is ${appContext.getString(R.string.app_name)}"
        )
        sharedPreferences.getBoolean("hello world", false)
    }

    fun iExist() {
        Log.d("MainViewModel", "Called iExist just to show this viewmodel is properly injected")
    }
}
