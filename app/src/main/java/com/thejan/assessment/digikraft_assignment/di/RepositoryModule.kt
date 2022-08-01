package com.thejan.assessment.digikraft_assignment.di

import com.thejan.assessment.digikraft_assignment.bike.StationRepository
import com.thejan.assessment.digikraft_assignment.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun stationRepository(apiService: ApiService) =
        StationRepository(apiService)

}