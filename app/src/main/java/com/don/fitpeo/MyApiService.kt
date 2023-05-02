package com.don.fitpeo

import com.don.fitpeo.models.ResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface MyApiService {

    @GET("/photos?_start=0&_limit=5")
    suspend fun getData(): Response<List<ResponseItem>>
}