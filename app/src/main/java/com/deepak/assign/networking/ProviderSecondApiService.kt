package com.deepak.assign.networking

import com.deepak.assign.entity.SearchModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Service interface for all API calls.
 */
interface ProviderSecondApiService {
    @GET("jobs/search.json")
    fun getPositions(@Query("query") query: String): Flowable<List<SearchModel>>
}
