package com.example.contestvault.data.codechef

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val retrofit = Retrofit
    .Builder()
    .baseUrl("https://codechef-api.vercel.app")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val codechefService = retrofit.create(CodeChefApiService::class.java)

interface CodeChefApiService {
    @GET("handle/{handle}")
    suspend fun getUserProfile(@Path("handle") handle: String): CodeChefUserData
}