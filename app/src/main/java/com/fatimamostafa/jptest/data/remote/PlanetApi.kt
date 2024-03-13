package com.fatimamostafa.jptest.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface PlanetApi {
   @GET("planets")
   suspend fun getPlanetsPage(@Query("page") page: Int) : PlanetResponse

    @GET("planets")
    suspend fun getPlanets() : PlanetResponse

   companion object {
       const val BASE_URL = "https://www.swapi.tech/api/"
   }
}