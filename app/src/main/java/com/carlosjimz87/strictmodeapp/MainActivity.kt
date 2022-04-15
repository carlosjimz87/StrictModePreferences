package com.carlosjimz87.strictmodeapp

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity

const val MY_PREf = "MY_PREF"

class MainActivity : AppCompatActivity() {
    private var sharedPrefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Thread {
            sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        }.start()

        backgroundAccessPrefs()

    }

    private fun backgroundAccessPrefs() {
        Thread {
            sharedPrefs?.getInt(MY_PREf, -1)
        }.start()

    }
}