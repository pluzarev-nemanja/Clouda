package com.example.data.model

import com.google.gson.annotations.SerializedName

data class CloudsX(
    @SerializedName("all")
    val cloudinessPercentage: Int
)