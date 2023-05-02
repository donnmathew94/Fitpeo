package com.don.fitpeo.data.repository

import com.don.fitpeo.MyApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val myApiService: MyApiService) {
    suspend fun getData() =
        myApiService.getData()
}