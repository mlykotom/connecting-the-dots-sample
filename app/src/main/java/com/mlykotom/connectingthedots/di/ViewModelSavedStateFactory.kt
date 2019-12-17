package com.mlykotom.connectingthedots.di

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * General factory for ViewModels with SavedStateHandle.
 * This factory allows having all ViewModels in one InjectingSavedStateViewModelFactory
 *
 * Use With @AssistedInject.Factory:
 *
 * ```
 * @AssistedInject.Factory
 * interface Factory: ViewModelSavedStateFactory<SomeViewModel>
 * ```
 */
interface ViewModelSavedStateFactory<T : ViewModel> {
    fun create(savedStateHandle: SavedStateHandle): T
}

