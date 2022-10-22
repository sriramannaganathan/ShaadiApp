package com.pravinkumarp.shadiassignment.data.service

import com.pravinkumarp.shadiassignment.model.Matches
import retrofit2.http.GET
import retrofit2.http.Query

interface ShaadiService {
    @GET("api")
    suspend fun getMatches(@Query("results") results: String): Matches
}