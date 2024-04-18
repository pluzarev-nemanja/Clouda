package com.example.weatherapp.common.util

import com.example.domain.util.Constants
import java.text.SimpleDateFormat

object DateFormatter {

    fun Long.formatDate(dateFormat: String): String = SimpleDateFormat(dateFormat).format(this * 1000)

}