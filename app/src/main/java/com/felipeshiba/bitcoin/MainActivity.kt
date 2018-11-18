package com.felipeshiba.bitcoin

import android.app.ActivityOptions
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.felipeshiba.core.Activities
import com.felipeshiba.core.intentTo

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPostResume() {
        super.onPostResume()

        startActivity(
            intentTo(Activities.Chart),
            ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
        )
    }
}
