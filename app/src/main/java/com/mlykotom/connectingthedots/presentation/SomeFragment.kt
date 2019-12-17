package com.mlykotom.connectingthedots.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mlykotom.connectingthedots.appComponent
import com.mlykotom.connectingthedots.di.AssistedSavedStateViewModelFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

/**
 * Just simple example (without UI) to show, how you can use the SavedStateHandle from base class.
 * This way, you just need to "get" your ViewModel by calling "by viewModels".
 */
class SomeFragment : BaseFragment() {
    /**
     * If using AndroidX fragment-ktx dependency, you may use this to simplify getting ViewModel
     */
    private val viewModel: SomeViewModel by viewModels()

    override fun onAttach(context: Context) {
        appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SomeFragment", "${viewModel.some.value}")
    }
}


class SomeViewModel @AssistedInject constructor(
    private val sharedPreferences: SharedPreferences,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<SomeViewModel>

    val some = savedStateHandle.getLiveData("some", 0)

    init {
        Log.d("SomeViewModel", "${some.value}")
    }
}
