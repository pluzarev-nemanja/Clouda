package com.example.weatherapp.dailyWeather.airPollution.mapper

import com.example.domain.model.ErrorResponse
import com.example.weatherapp.airPollution.mapper.ThrowableToAirPollutionUIStateErrorMapper
import com.example.weatherapp.airPollution.uiState.AirPollutionUIState
import org.junit.Assert
import org.junit.Test

class ThrowableToAirPollutionUIStateErrorMapperTest {

    private val mapper = ThrowableToAirPollutionUIStateErrorMapper()

    @Test
    fun `given ErrorResponse Network when mappingObjects then return AirPollutionUIState Error Internet`() {

        val expected =
            AirPollutionUIState.Error.Internet(message = "Check your internet connection!")

        val network = ErrorResponse.Network

        val actual = mapper.mappingObjects(network)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `given ErrorResponse Host when mappingObjects then return AirPollutionUIState Error Server`() {

        val expected = AirPollutionUIState.Error.Server(message = "Server not responding!")

        val host = ErrorResponse.Host

        val actual = mapper.mappingObjects(host)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `given ErrorResponse Unknown when mappingObjects then return AirPollutionUIState Error Unknown`() {

        val expected = AirPollutionUIState.Error.Unknown(message = "Unknown error occurred!")

        val unknown = ErrorResponse.Unknown

        val actual = mapper.mappingObjects(unknown)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `given IndexOutOfBoundsException when mappingObjects then return AirPollutionUIState Error Unknown`() {

        val expected = AirPollutionUIState.Error.Unknown(message = "Unknown error occurred!")

        val indexOutOfBoundsException = IndexOutOfBoundsException()

        val actual = mapper.mappingObjects(indexOutOfBoundsException)

        Assert.assertEquals(expected, actual)
    }

}