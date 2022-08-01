package com.thejan.assessment.digikraft_assignment.data.remote

import com.thejan.assessment.digikraft_assignment.data.model.Features
import com.thejan.assessment.digikraft_assignment.data.model.Station
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("map_service.html")
    suspend fun getMapService(
        @Query("mtype") mType: String,
        @Query("co") co: String
    ): Response<Station>
}