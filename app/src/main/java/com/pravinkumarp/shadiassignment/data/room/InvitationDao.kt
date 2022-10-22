package com.pravinkumarp.shadiassignment.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pravinkumarp.shadiassignment.model.Invitation

@Dao
interface InvitationDao {
    @Query("SELECT * FROM invitation")
    fun getAll(): List<Invitation>

    @Insert
    fun insertAll(vararg users: Invitation)

    @Update
    fun update(user: Invitation)
}