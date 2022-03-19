package com.example.sahabss.di

import android.content.Context
import com.example.sahabss.data.remote.network.EmployeeApiService
import com.example.sahabss.data.remote.repository.EmployeeRepository
import com.example.sahabss.data.remote.repository.EmployeeRepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    internal fun provideEmployeeRepository(
        apiService: EmployeeApiService
    ): EmployeeRepository = EmployeeRepositoryImpl(apiService)

}