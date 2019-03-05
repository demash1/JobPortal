package com.deepak.assign.ui

import com.deepak.assign.networking.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    fun getPosition() = apiService.getPositions()


}