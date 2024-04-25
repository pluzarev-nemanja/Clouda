package com.example.weatherapp.common.pref

import android.content.Context
import android.content.SharedPreferences
import com.example.weatherapp.common.util.Constants.DARK_MODE_PREF

class PreferencesManagerImpl(
    context: Context
): PreferencesManager {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(DARK_MODE_PREF,Context.MODE_PRIVATE)

    override fun saveData(key: String, value: Boolean) {
           sharedPreferences
               .edit()
               .putBoolean(key,value)
               .apply()
    }

    override fun getData(key: String, defaultValue: Boolean): Boolean = sharedPreferences.getBoolean(key,defaultValue)

}