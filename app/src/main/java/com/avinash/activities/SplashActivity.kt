package com.avinash.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.avinash.R
import com.avinash.activities.name.NameActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startSplash()
    }


    /*
    * method to navigate to next screen after 2 seconds
    * */
    private fun startSplash() {
        Handler().postDelayed({ startActivity(Intent(this, NameActivity::class.java)) },2000)
    }


}
