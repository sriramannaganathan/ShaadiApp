package com.pravinkumarp.shadiassignment.di

import androidx.room.Room
import com.pravinkumarp.shadiassignment.data.room.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val DATABASE_NAME = "ShaadiDB"

val roomModule = module {

//    fun provideDatabase(application: Application): AppDatabase {
//        return Room.databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME)
//            .fallbackToDestructiveMigration()
//            .allowMainThreadQueries()
//            .build()
//    }
//
//    fun provideDao(database: AppDatabase): InvitationDao {
//        return database.invitationDao()
//    }
//
//    single { provideDatabase(androidApplication()) }
//    single { provideDao(get()) }

    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    single {
        get<AppDatabase>().invitationDao()
    }
}