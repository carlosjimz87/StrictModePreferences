package com.carlosjimz87.strictmodeapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import java.lang.Thread.MAX_PRIORITY

const val MY_PREf = "MY_PREF"

class MainActivity : AppCompatActivity() {
    private var sharedPrefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val thread =Thread{
            sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        }

        thread.priority = MAX_PRIORITY
        thread.start()

        backgroundAccessPrefs()

    }

    private fun backgroundAccessPrefs() {
        Thread {
            sharedPrefs?.getInt(MY_PREf, -1)
        }.start()

    }
}