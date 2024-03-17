package com.fatimamostafa.jptest.data.remote

import retrofit2.http.GET

interface PlanetApi {
    @GET("planets")
    suspend fun getPlanets() : PlanetResponse

   companion object {
       const val BASE_URL = "https://www.swapi.tech/api/"
   }
}