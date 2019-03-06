package com.deepak.assign.networking

import com.deepak.assign.entity.PositionModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Service interface for all API calls.
 */
interface ProviderFirstApiService {
    @GET("positions.json")
    fun getPositions(
        @Query("description") description: String,
        @Query("location") location: String
    ): Flowable<List<PositionModel>>
}
