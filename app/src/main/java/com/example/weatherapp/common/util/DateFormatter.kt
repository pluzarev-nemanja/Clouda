package com.example.weatherapp.common.util

import com.example.domain.util.Constants
import java.text.SimpleDateFormat

object DateFormatter {

    fun Long.formatDate(): String = SimpleDateFormat(Constants.DATE_FORMAT).format(this * 1000)

}