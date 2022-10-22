package com.pravinkumarp.shadiassignment.data.repository

import com.pravinkumarp.shadiassignment.model.Matches

interface Repository {
    suspend fun getMatches(results: String): Matches
    suspend fun getMatchesFromLocal(results: String): Matches
    suspend fun update(invitation: Matches.Invitation)
}