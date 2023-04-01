package com.example.taskhumanhometest.di

import android.content.Context
import com.example.taskhumanhometest.data.remote.SkillsApi
import com.example.taskhumanhometest.data.util.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDefaultRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api-devenv.taskhuman.com/v0.8/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideSkillsApi(retrofit: Retrofit): SkillsApi {
        return retrofit.create(SkillsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSessionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManager(context)
    }
}