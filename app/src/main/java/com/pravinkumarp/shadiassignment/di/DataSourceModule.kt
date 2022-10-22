package com.pravinkumarp.shadiassignment.di

import com.pravinkumarp.shadiassignment.data.local.LocalDataSource
import com.pravinkumarp.shadiassignment.data.local.LocalDataSourceImpl
import com.pravinkumarp.shadiassignment.data.remote.RemoteDataSource
import com.pravinkumarp.shadiassignment.data.remote.RemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<RemoteDataSource> {
        RemoteDataSourceImpl(shaadiService = get())
    }
    single<LocalDataSource> {
        LocalDataSourceImpl(invitationDao = get())
    }
}