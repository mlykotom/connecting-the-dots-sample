package com.mlykotom.connectingthedots.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mlykotom.connectingthedots.R
import com.mlykotom.connectingthedots.appComponent
import com.mlykotom.connectingthedots.di.InjectingSavedStateViewModelFactory
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
