package com.pravinkumarp.shadiassignment.data.local

import com.pravinkumarp.shadiassignment.data.room.InvitationDao
import com.pravinkumarp.shadiassignment.model.Invitation
import com.pravinkumarp.shadiassignment.model.Matches


class LocalDataSourceImpl(private val invitationDao: InvitationDao) : LocalDataSource {
    override suspend fun getMatches(): Matches {
        val items = invitationDao.getAll()
        return Matches(null, items.map {
            Matches.Invitation.convertFromLocal(it.data!!)
        })
    }

    override suspend fun addMatches(invitations: List<Matches.Invitation>) {
        invitationDao.insertAll(*invitations.map {
            Invitation.convertFromRemote(it)
        }.toTypedArray())
    }

    override suspend fun updateInvitation(invitation: Matches.Invitation) {
        invitationDao.update(Invitation.convertFromRemote(invitation))
    }
}