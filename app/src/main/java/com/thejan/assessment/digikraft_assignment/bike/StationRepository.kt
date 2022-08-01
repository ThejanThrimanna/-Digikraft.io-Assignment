package com.thejan.assessment.digikraft_assignment.bike

import com.thejan.assessment.digikraft_assignment.data.remote.NetworkResult
import com.thejan.assessment.digikraft_assignment.data.model.Station
import com.thejan.assessment.digikraft_assignment.data.remote.ApiService
import com.thejan.assessment.digikraft_assignment.data.remote.BaseApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StationRepository @Inject constructor(
    private val apiService: ApiService
) : BaseApiResponse() {
    suspend fun fetchMapServices(): Flow<NetworkResult<Station>> {
        return flow {
            val response =
                safeApiCall { apiService.getMapService("pub_transport", "stacje_rowerowe") }
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}