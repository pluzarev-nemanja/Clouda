package com.example.weatherapp.common.util

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

object DateFormatter {

    fun Long.formatDate(dateFormat: String) = SimpleDateFormat(dateFormat).format(this * 1000)

    fun formatLocalTime() = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))
}