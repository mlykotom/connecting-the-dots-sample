package com.mlykotom.connectingthedots.di

import androidx.lifecycle.ViewModel
import com.mlykotom.connectingthedots.presentation.MainViewModel
import com.mlykotom.connectingthedots.presentation.OtherViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@AssistedModule
@Module(includes = [AssistedInject_BuilderModule::class])
abstract class BuilderModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(vm: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OtherViewModel::class)
    abstract fun bindsOtherViewModel(f: OtherViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}
