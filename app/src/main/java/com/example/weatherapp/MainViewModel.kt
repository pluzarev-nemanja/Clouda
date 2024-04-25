package com.example.weatherapp

import androidx.lifecycle.ViewModel
import com.example.weatherapp.common.pref.PreferencesManager
import com.example.weatherapp.common.util.Constants.DARK_MODE_PREF
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private var mutableIsInDarkTheme: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isInDarkTheme: StateFlow<Boolean> = mutableIsInDarkTheme.asStateFlow()

    init {
        mutableIsInDarkTheme.value = preferencesManager.getData(DARK_MODE_PREF,false)
    }

    fun saveTheme(key: String, value: Boolean) {
        preferencesManager.saveData(key = key, value = value)
        mutableIsInDarkTheme.value = value
    }

}