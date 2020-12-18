package com.slavainc.myapplication.feature.main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.slavainc.myapplication.R
import com.slavainc.myapplication.feature.main.vm.MainViewModel
import com.slavainc.myapplication.utils.Resource.Status.ERROR
import com.slavainc.myapplication.utils.Resource.Status.SUCCESS
import com.slavainc.myapplication.utils.observe
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.subscribe()
        observe(viewModel.subscribesLD) { resource ->
            when (resource.status) {
                SUCCESS -> {
                    val text = when (val trips = resource.data) {
                        null -> getString(R.string.subscriptionError)
                        -1 -> getString(R.string.tripCancelled)
                        else -> getString(R.string.tripBooked, trips)
                    }
                    Snackbar.make(findViewById(R.id.main_frame_layout), text, Snackbar.LENGTH_LONG).show()
                }
                ERROR -> {
                }
            }
        }
    }
}
