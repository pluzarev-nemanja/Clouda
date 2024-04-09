package com.example.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponseBody(
    @SerializedName("alerts")
    val alerts: List<Alert>?,
    @SerializedName("current")
    val currentReport: Current?,
    @SerializedName("daily")
    val dailyReports: List<Daily>?,
    @SerializedName("hourly")
    val hourlyReports: List<Hourly>?,
    @SerializedName("lat")
    val latitude: Double?,
    @SerializedName("lon")
    val longitude: Double?,
    @SerializedName("minutely")
    val minutelyReports: List<Minutely>?,
    @SerializedName("timezone")
    val timeZone: String?,
    @SerializedName("timezone_offset")
    val timezoneOffset: Int?
)