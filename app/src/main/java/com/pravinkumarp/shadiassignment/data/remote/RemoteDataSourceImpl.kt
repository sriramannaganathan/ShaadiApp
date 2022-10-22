package com.pravinkumarp.shadiassignment.data.remote

import com.pravinkumarp.shadiassignment.data.service.ShaadiService
import com.pravinkumarp.shadiassignment.model.Matches

class RemoteDataSourceImpl(private val shaadiService: ShaadiService): RemoteDataSource {
    override suspend fun getMatches(results: String): Matches {
        return shaadiService.getMatches(results);
    }
}