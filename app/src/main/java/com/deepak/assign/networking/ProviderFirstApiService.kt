package com.deepak.assign.networking

import com.deepak.assign.entity.PositionModel
import io.reactivex.Flowable
import retrofit2.http.GET


/**
 * Service interface for all API calls.
 */
interface ProviderFirstApiService {
    @GET("positions.json")
    fun getPositions(): Flowable<List<PositionModel>>
}
