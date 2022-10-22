package com.pravinkumarp.shadiassignment.data.remote

import com.pravinkumarp.shadiassignment.model.Matches

interface RemoteDataSource {
    suspend fun getMatches(results: String): Matches
}