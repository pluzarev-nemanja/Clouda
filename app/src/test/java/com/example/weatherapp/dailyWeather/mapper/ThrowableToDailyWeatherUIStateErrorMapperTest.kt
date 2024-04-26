package com.example.weatherapp.dailyWeather.mapper

import android.net.http.HttpException
import com.example.domain.model.ErrorResponse
import com.example.weatherapp.dailyWeather.uiState.DailyWeatherUIState
import org.junit.Assert.assertEquals
import org.junit.Test

class ThrowableToDailyWeatherUIStateErrorMapperTest {

    private val mapper = ThrowableToDailyWeatherUIStateErrorMapper()


    @Test
    fun `given ErrorResponse Network when mappingObjects then return DailyWeatherUIState Error Internet`(){

        val expected = DailyWeatherUIState.Error.Internet(message = "Check your internet connection!")

        val network = ErrorResponse.Network

        val actual = mapper.mappingObjects(network)

        assertEquals(expected, actual)

    }

    @Test
    fun `given ErrorResponse Host when mappingObjects then return DailyWeatherUIState Error Server`(){

        val expected = DailyWeatherUIState.Error.Server(message = "Server not responding!")

        val host = ErrorResponse.Host

        val actual = mapper.mappingObjects(host)

        assertEquals(expected, actual)
    }

    @Test
    fun `given ErrorResponse Unknown when mappingObjects then return DailyWeatherUIState Error Unknown`(){

        val expected = DailyWeatherUIState.Error.Unknown(message = "Unknown error occurred!")

        val unknown = ErrorResponse.Unknown

        val actual = mapper.mappingObjects(unknown)

        assertEquals(expected, actual)
    }

    @Test
    fun `given IndexOutOfBoundsException when mappingObjects then return DailyWeatherUIState Error Unknown`(){

        val expected = DailyWeatherUIState.Error.Unknown(message = "Unknown error occurred!")

        val indexOutOfBoundsException = IndexOutOfBoundsException()

        val actual = mapper.mappingObjects(indexOutOfBoundsException)

        assertEquals(expected, actual)
    }

    @Test
    fun `given HttpException when mappingObjects then return DailyWeatherUIState Error Unknown`(){

        val expected = DailyWeatherUIState.Error.Unknown(message = "Unknown error occurred!")

        val http = HttpException(null,null)

        val actual = mapper.mappingObjects(http)

        assertEquals(expected, actual)
    }

}