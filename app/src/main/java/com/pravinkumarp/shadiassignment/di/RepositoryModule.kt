package com.pravinkumarp.shadiassignment.di

import com.pravinkumarp.shadiassignment.data.repository.Repository
import com.pravinkumarp.shadiassignment.data.repository.RepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> {
        RepositoryImpl(remoteDataSource = get(), localDataSource = get())
    }
}