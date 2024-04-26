package com.example.weatherapp.weeklyWeather.mapper

import com.example.domain.model.ErrorResponse
import com.example.weatherapp.dailyWeather.uiState.DailyWeatherUIState
import com.example.weatherapp.weeklyWeather.uiState.WeeklyWeatherUIState
import org.junit.Assert
import org.junit.Test

class ThrowableToWeeklyWeatherUIStateErrorMapperTest {

    private val mapper = ThrowableToWeeklyWeatherUIStateErrorMapper()

    @Test
    fun `given ErrorResponse Network when mappingObjects then return WeeklyWeatherUIState Error Internet`() {

        val expected =
            WeeklyWeatherUIState.Error.Internet(message = "Check your internet connection!")

        val network = ErrorResponse.Network

        val actual = mapper.mappingObjects(network)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `given ErrorResponse Host when mappingObjects then return WeeklyWeatherUIState Error Server`() {

        val expected = WeeklyWeatherUIState.Error.Server(message = "Server not responding!")

        val host = ErrorResponse.Host

        val actual = mapper.mappingObjects(host)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `given ErrorResponse Unknown when mappingObjects then return WeeklyWeatherUIState Error Unknown`(){

        val expected = WeeklyWeatherUIState.Error.Unknown(message = "Unknown error occurred!")

        val unknown = ErrorResponse.Unknown

        val actual = mapper.mappingObjects(unknown)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `given IndexOutOfBoundsException when mappingObjects then return WeeklyWeatherUIState Error Unknown`(){

        val expected = WeeklyWeatherUIState.Error.Unknown(message = "Unknown error occurred!")

        val indexOutOfBoundsException = IndexOutOfBoundsException()

        val actual = mapper.mappingObjects(indexOutOfBoundsException)

        Assert.assertEquals(expected, actual)
    }

}