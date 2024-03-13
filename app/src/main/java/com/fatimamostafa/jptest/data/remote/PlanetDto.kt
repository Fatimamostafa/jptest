package com.fatimamostafa.jptest.data.remote

data class PlanetResponse(
    val message: String,
    val totalRecords: Int,
    val totalPages: Int,
    val previous: String?,
    val next: String?,
    val url: String,
    val results: List<PlanetDto>
)

data class PlanetDto(
    val name: String,
    val uid: String,
    val url: String,
)