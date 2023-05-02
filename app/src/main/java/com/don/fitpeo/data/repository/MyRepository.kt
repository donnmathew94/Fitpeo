package com.don.fitpeo.data.repository

import com.don.fitpeo.models.ResponseItem
import com.don.fitpeo.utils.BaseApiResponse
import com.don.fitpeo.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class MyRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {
    suspend fun getData(): Flow<NetworkResult<List<ResponseItem>>> {
        return flow<NetworkResult<List<ResponseItem>>> {
            emit(safeApiCall { remoteDataSource.getData() })
        }.flowOn(Dispatchers.IO)
    }
}