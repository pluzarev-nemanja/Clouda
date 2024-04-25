package com.example.weatherapp.common.pref

interface PreferencesManager {

    fun saveData(key: String,value: Boolean)

    fun getData(key: String,defaultValue: Boolean) : Boolean
}