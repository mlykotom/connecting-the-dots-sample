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
 * interface Factory: AssistedSavedStateViewModelFactory<SomeViewModel>
 * ```
 * In your Dagger module (annotated with `@AssistedModule`) add binding of your AssistedInject.Factory to this abstraction:
 * ```
 * @Binds
 * @IntoMap
 * @ViewModelKey(SomeViewModel::class)
 * abstract fun bindSomeViewModelFactory(factory: SomeViewModel.Factory) : AssistedSavedStateViewModelFactory<out ViewModel>
 * ```
 *
 * @see com.squareup.inject.assisted.dagger2.AssistedModule
 * @see com.squareup.inject.assisted.AssistedInject.Factory
 */
interface AssistedSavedStateViewModelFactory<T : ViewModel> {
    fun create(savedStateHandle: SavedStateHandle): T
}

