package com.carlosjimz87.strictmodeapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager

const val MY_PREf = "MY_PREF"

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPrefs= PreferenceManager.getDefaultSharedPreferences(this)

        backgroundAccessPrefs()
    }

    fun backgroundAccessPrefs(){
        Thread{
            sharedPrefs.getInt(MY_PREf,-1)
        }.start()
    }
}