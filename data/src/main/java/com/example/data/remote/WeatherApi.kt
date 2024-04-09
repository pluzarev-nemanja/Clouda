package com.example.data.remote

import com.example.data.model.WeatherResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {


    /***
     * getWeathersData vraca podatke za taj dan
     * getWeathersDataInCustomTime mozemo da vidimo prognozu u nazad i 4 datan unapred
     * za prognozu u predhodnih 5 dana moramo ponovo pozvati api 5 puta
     */
    @GET("/onecall")
    suspend fun getWeathersData(
        @Query("lat") latitude: Double = 0.0,
        @Query("lon") longitude: Double = 0.0,
        @Query("appid") apiKey: String
    ): WeatherResponseBody

    @GET("/onecall")
    suspend fun getWeathersDataInCustomTime(
        @Query("lat") latitude: Double = 0.0,
        @Query("lon") longitude: Double = 0.0,
        @Query("dt") time: Int = 0,
        @Query("appid") apiKey: String
    ): WeatherResponseBody

}