package com.example.data.model

import com.google.gson.annotations.SerializedName

data class Minutely(
    @SerializedName("dt")
    val time: Int,
    @SerializedName("precipitation")
    val precipitation: Int
)