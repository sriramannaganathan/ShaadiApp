package com.pravinkumarp.shadiassignment.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pravinkumarp.shadiassignment.model.Invitation

@Database(entities = [Invitation::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun invitationDao(): InvitationDao
}