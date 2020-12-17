package com.slavainc.myapplication.feature.main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.slavainc.myapplication.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        lifecycleScope.launch {
//            apolloClient(this@MainActivity).subscribe(TripsBookedSubscription()).toFlow()
//                .retryWhen { _, attempt ->
//                    delay(attempt * 1000)
//                    true
//                }
//                .collect {
//                    val trips = it.data?.tripsBooked
//                    val text = when {
//                        trips == null -> getString(R.string.subscriptionError)
//                        trips == -1 -> getString(R.string.tripCancelled)
//                        else -> getString(R.string.tripBooked, trips)
//                    }
//                    Snackbar.make(
//                        findViewById(R.id.main_frame_layout),
//                        text,
//                        Snackbar.LENGTH_LONG
//                    ).show()
//                }
//        }
    }
}
