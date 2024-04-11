package com.example.data.model

import com.google.gson.annotations.SerializedName

data class Pollution(
    val components: Components,
    @SerializedName("dt")
    val time: Int,
    val main: MainX
)