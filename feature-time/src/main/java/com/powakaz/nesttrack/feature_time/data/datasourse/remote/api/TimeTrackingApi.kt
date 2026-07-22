package com.powakaz.nesttrack.feature_time.data.datasourse.remote.api

import com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.ActivitiesResponseDto
import com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.ConcessionListResponseDto
import com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.TimeBalanceResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeTrackingApi {

    @GET("time/balances")
    suspend fun getBalanceConcession(
        @Query("user_id") userId: Int
    ): List<TimeBalanceResponseDto>


    @GET("time/activities")
    suspend fun getListActivities(
    ): List<ActivitiesResponseDto>


    @GET("time/logs")
    suspend fun getListConcession(
        @Query("page") page: Int
    ): ConcessionListResponseDto
}