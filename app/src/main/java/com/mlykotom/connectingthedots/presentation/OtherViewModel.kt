package com.mlykotom.connectingthedots.presentation

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mlykotom.connectingthedots.R
import com.mlykotom.connectingthedots.di.AssistedSavedStateViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

/**
 * ViewModel with usage of @AssistedInject
 */
class OtherViewModel @AssistedInject constructor(
    private val sharedPreferences: SharedPreferences,
    private val appContext: Context,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<OtherViewModel>

    val counter = savedStateHandle.getLiveData("counter", 0)

    init {
        // if wouldn't be injected properly, it would crash
        Log.d(
            "OtherViewModel",
            "Application is ${appContext.getString(R.string.app_name)}"
        )
        Log.d("OtherViewModel", "Counter is ${counter.value}")
    }

    fun onPlusClick() {
        counter.value = counter.value!! + 1
    }
}
