package com.pravinkumarp.shadiassignment.data.repository

import com.pravinkumarp.shadiassignment.data.local.LocalDataSource
import com.pravinkumarp.shadiassignment.data.remote.RemoteDataSource
import com.pravinkumarp.shadiassignment.model.Matches

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {
    override suspend fun getMatches(results: String): Matches {
        return remoteDataSource.getMatches(results).let {
            // store in local database
            localDataSource.addMatches(it.invitations)
            // fetch from local and serve
            localDataSource.getMatches()
        }
    }

    override suspend fun getMatchesFromLocal(results: String): Matches {
        return localDataSource.getMatches()
    }

    override suspend fun update(invitation: Matches.Invitation) {
        localDataSource.updateInvitation(invitation)
    }
}