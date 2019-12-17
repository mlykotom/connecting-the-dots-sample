package com.mlykotom.connectingthedots.presentation

import androidx.fragment.app.Fragment
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModelProvider
import com.mlykotom.connectingthedots.di.InjectingSavedStateViewModelFactory
import javax.inject.Inject

abstract class BaseFragment : Fragment(), HasDefaultViewModelProviderFactory {
    @Inject
    lateinit var defaultViewModelFactory: InjectingSavedStateViewModelFactory

    /**
     * This method androidx uses for `by viewModels` method.
     * We can set out injecting factory here and therefore don't touch it again
     */
    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory =
        defaultViewModelFactory.create(this, arguments)
}
