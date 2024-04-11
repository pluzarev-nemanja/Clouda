package com.example.data.model

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val cloudinessPercentage: Int
)