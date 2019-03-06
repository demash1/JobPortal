package com.deepak.assign.ui

import com.deepak.assign.networking.ProviderFirstApiService
import com.deepak.assign.networking.ProviderSecondApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val providerFirstApiService: ProviderFirstApiService,
    private val providerSecondApiService: ProviderSecondApiService
) {

    fun getPosition() = providerFirstApiService.getPositions()
    fun getPositionSecond(query: String) = providerSecondApiService.getPositions(query)
}