package com.example.data.model.reponses

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("`1h`")
    val rainVolume: Double
)