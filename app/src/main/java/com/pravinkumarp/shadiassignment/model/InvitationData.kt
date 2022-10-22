package com.pravinkumarp.shadiassignment.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson

@Entity
data class Invitation(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "data") val data: String?,
) {
    companion object {
        fun convertFromRemote(invitation: Matches.Invitation): Invitation {
            return Invitation(invitation.login.uuid, Gson().toJson(invitation))
        }
    }
}
