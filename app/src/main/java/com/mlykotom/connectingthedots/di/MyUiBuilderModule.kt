package com.mlykotom.connectingthedots.di

import androidx.lifecycle.ViewModel
import com.mlykotom.connectingthedots.presentation.MainViewModel
import com.mlykotom.connectingthedots.presentation.OtherViewModel
import com.mlykotom.connectingthedots.presentation.SomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MyUiBuilderModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(vm: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OtherViewModel::class)
    abstract fun bindsOtherViewModel(f: OtherViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(SomeViewModel::class)
    abstract fun bindsSomeViewModel(f: SomeViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}
