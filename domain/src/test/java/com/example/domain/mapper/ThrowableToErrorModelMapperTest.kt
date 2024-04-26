package com.example.domain.mapper

import android.net.http.HttpException
import com.example.domain.model.ErrorResponse
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException
import java.net.UnknownHostException

class ThrowableToErrorModelMapperTest {


    private val mapper = mockk<ThrowableToErrorModelMapper>()


    @Test
    fun `given HttpException when mappingObjects then returns ErrorResponse Host`() {


        val expected = ErrorResponse.Host
        val httpException = HttpException(null,null)

        every { mapper.mappingObjects(httpException) } returns expected

        val actual = mapper.mappingObjects(httpException)

        assertEquals(expected,actual)

    }

    @Test
    fun `given UnknownHostException when mappingObjects then returns ErrorResponse Network`(){

        val expected = ErrorResponse.Network
        val unknownHostException = UnknownHostException()

        every { mapper.mappingObjects(unknownHostException) } returns expected

        val actual = mapper.mappingObjects(unknownHostException)

        assertEquals(expected, actual)
    }

    @Test
    fun `given IOException when mappingObjects then returns ErrorResponse Network`(){

        val expected = ErrorResponse.Network
        val ioException = IOException()

        every { mapper.mappingObjects(ioException) } returns expected

        val actual = mapper.mappingObjects(ioException)

        assertEquals(expected, actual)

    }


    @Test
    fun `given IndexOutOfBoundsException when mappingObject then returns ErrorResponse Unknown`(){

        val expected = ErrorResponse.Unknown
        val indexOutOfBoundsException = IndexOutOfBoundsException()

        every { mapper.mappingObjects(indexOutOfBoundsException) } returns  expected

        val actual = mapper.mappingObjects(indexOutOfBoundsException)

        assertEquals(expected,actual)
    }


}