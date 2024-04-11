package com.example.data.remote

import com.example.data.model.CurrentWeatherResponse
import com.example.data.model.WeeklyAirPollutionsResponse
import com.example.data.model.WeeklyWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {


    @GET("weather?")
    suspend fun getCurrentWeathersData(
        @Query("lat") latitude: Double = 0.0,
        @Query("lon") longitude: Double = 0.0,
        @Query("appid") apiKey: String,
        @Query("units") units : String = "metric",
    ): CurrentWeatherResponse

    @GET("forecast?")
    suspend fun getWeeklyWeatherData(
        @Query("lat") latitude: Double = 0.0,
        @Query("lon") longitude: Double = 0.0,
        @Query("appid") apiKey: String,
        @Query("units") units : String = "metric",
    ): WeeklyWeatherResponse

    @GET("air_pollution/history?")
    suspend fun getWeeklyAirPollutionData(
        @Query("lat") latitude: Double = 0.0,
        @Query("lon") longitude: Double = 0.0,
        @Query("start") startingDay: Int = 0,
        @Query("end") endingDay: Int = 0,
        @Query("appid") apiKey: String,
    ): WeeklyAirPollutionsResponse



}