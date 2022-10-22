package com.pravinkumarp.shadiassignment.data.local

import com.pravinkumarp.shadiassignment.model.Matches
import com.pravinkumarp.shadiassignment.model.Matches.Invitation

interface LocalDataSource {
    suspend fun getMatches(): Matches
    suspend fun addMatches(invitations: List<Invitation>)
    suspend fun updateInvitation(invitation: Invitation)
}