package com.mlykotom.connectingthedots.di

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.multibindings.Multibinds

@Module
abstract class CommonUiModule {
    @Multibinds
    abstract fun viewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>

    @Multibinds
    abstract fun assistedViewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards AssistedSavedStateViewModelFactory<out ViewModel>>
}
