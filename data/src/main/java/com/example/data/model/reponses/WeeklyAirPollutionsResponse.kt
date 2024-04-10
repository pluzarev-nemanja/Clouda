package com.example.data.model.reponses

data class WeeklyAirPollutionsResponse(
    val coord: CoordX,
    val list: List<Pollution>
)