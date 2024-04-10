package com.example.data.model.reponses

import com.google.gson.annotations.SerializedName

data class CloudsX(
    @SerializedName("all")
    val cloudinessPercentage: Int
)