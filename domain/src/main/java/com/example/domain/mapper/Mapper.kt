package com.example.domain.mapper

interface Mapper <in Input , out Output> {

    fun mappingObjects(input: Input): Output
}