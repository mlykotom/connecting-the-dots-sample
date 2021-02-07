package com.mlykotom.connectingthedots.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mlykotom.connectingthedots.R
import com.mlykotom.connectingthedots.appComponent
import com.mlykotom.connectingthedots.di.InjectingSavedStateViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var abstractViewModelFactory: dagger.Lazy<InjectingSavedStateViewModelFactory>

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory =
        abstractViewModelFactory.get().create(this)

    // dagger ViewModel
    private val viewModel: MainViewModel by viewModels()

    // assisted ViewModel
    private val otherViewModel: OtherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        viewModel.iExist()

        val counterView = findViewById<TextView>(R.id.textview_counter)
        otherViewModel.counter.observe(this) {
            counterView.text = "Count is $it"
        }

        val buttonView = findViewById<Button>(R.id.button_increment)
        buttonView.setOnClickListener {
            otherViewModel.onPlusClick()
        }
    }
}
