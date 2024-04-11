package com.example.data.model

data class WeeklyAirPollutionsResponse(
    val coord: CoordX,
    val list: List<Pollution>
)