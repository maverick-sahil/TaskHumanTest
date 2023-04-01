package com.example.taskhumanhometest.di

import com.example.taskhumanhometest.data.remote.SkillsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSkillsApi(): SkillsApi {
        return Retrofit.Builder()
            .baseUrl("https://api-devenv.taskhuman.com/v0.8/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
}