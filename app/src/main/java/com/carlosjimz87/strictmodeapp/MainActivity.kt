package com.carlosjimz87.strictmodeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private val dataStore by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // write data store value
        lifecycleScope.launch {
            val text = read("KEY", "")
            val num = read("NUM", 0)

            withContext(Dispatchers.Main) {
                textView.text = "$text $num"
            }

        }

        // write data store value at click
        button.setOnClickListener {
            lifecycleScope.launch {

                val num = read("NUM", 0)

                save("KEY", "HELLO")
                save("NUM", num +1 )

                val text2 = read("KEY", "")
                val num2 = read("NUM", 0)

                withContext(Dispatchers.Main) {
                    textView.text = "$text2 $num2"
                }
            }
        }

    }

    private suspend fun save(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    private suspend fun save(key: String, value: Int) {
        val dataStoreKey = intPreferencesKey(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    private suspend fun read(key: String, default: String): String {
        val dataStoreKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey] ?: default
    }

    private suspend fun read(key: String, default: Int): Int {
        val dataStoreKey = intPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey] ?: default
    }
}