package com.deepak.assign.ui

import com.deepak.assign.networking.ProviderFirstApiService
import com.deepak.assign.networking.ProviderSecondApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
     val providerFirstApiService: ProviderFirstApiService,
     val providerSecondApiService: ProviderSecondApiService
) {

    fun getPosition(description: String, location: String) = providerFirstApiService.getPositions(description, location)
    fun getPositionSecond(query: String) = providerSecondApiService.getPositions(query)
}