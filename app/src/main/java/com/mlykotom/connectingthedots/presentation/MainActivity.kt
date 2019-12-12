package com.mlykotom.connectingthedots.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.mlykotom.connectingthedots.R
import com.mlykotom.connectingthedots.appComponent
import com.mlykotom.connectingthedots.di.InjectingSavedStateViewModelFactory
import com.mlykotom.connectingthedots.di.ViewModelAssistedFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var abstractViewModelFactory: InjectingSavedStateViewModelFactory

    lateinit var viewModel: MainViewModel
    lateinit var otherViewModel: OtherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)

        val factory = abstractViewModelFactory.create(this)

        // dagger ViewModel
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        // assisted ViewModel
        otherViewModel = ViewModelProvider(this, factory)[OtherViewModel::class.java]

        setContentView(R.layout.activity_main)

        val counterView = findViewById<TextView>(R.id.textview_counter)
        otherViewModel.counter.observe(this, Observer {
            counterView.text = "Count is $it"
        })

        val buttonView = findViewById<Button>(R.id.button_increment)
        buttonView.setOnClickListener {
            otherViewModel.onPlusClick()
        }
    }
}

class MainViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val appContext: Context
) : ViewModel() {

    init {
        // if wouldn't be injected properly, it would crash
        Log.d("MainViewModel", "Application is ${appContext.getString(R.string.app_name)}")
        sharedPreferences.getBoolean("hello world", false)
    }
}


class OtherViewModel @AssistedInject constructor(
    private val sharedPreferences: SharedPreferences,
    private val appContext: Context,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<OtherViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): OtherViewModel
    }

    val counter = savedStateHandle.getLiveData("counter", 0)

    init {
        // if wouldn't be injected properly, it would crash
        Log.d("OtherViewModel", "Application is ${appContext.getString(R.string.app_name)}")
        Log.d("OtherViewModel", "Counter is ${counter.value}")
    }

    fun onPlusClick() {
        counter.value = counter.value!! + 1
    }
}