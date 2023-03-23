package com.example.json
import retrofit2.http.GET

interface APIRequest {
    @GET("cities_new.json")
    suspend fun getData(): APIData
}