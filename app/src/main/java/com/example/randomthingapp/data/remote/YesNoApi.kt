package com.example.randomthingapp.data.remote

import com.example.randomthingapp.data.model.YesNoResponse
import retrofit2.http.GET

interface YesNoApi {
    @GET("api")
    suspend fun getAnswer(): YesNoResponse
}
