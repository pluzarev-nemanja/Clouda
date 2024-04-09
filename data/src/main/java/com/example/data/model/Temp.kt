package com.example.data.model

import com.google.gson.annotations.SerializedName

data class Temp(
    @SerializedName("day")
    val day: Double,
    @SerializedName("eve")
    val evening: Double,
    @SerializedName("max")
    val max: Double,
    @SerializedName("min")
    val min: Double,
    @SerializedName("morn")
    val morning: Double,
    @SerializedName("night")
    val night: Double
)