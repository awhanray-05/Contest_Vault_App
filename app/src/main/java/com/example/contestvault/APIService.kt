package com.example.contestvault

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit
    .Builder()
    .baseUrl("http://10.0.2.2:3000/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val resultService: APIService = retrofit.create(APIService::class.java)

interface APIService {
    @GET("scrape")
    suspend fun getResults(
        @Query("contestName") contestName: String,
        @Query("category") contestCategory: String
    ): ResultResponse
}