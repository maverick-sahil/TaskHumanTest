package com.example.taskhumanhometest.di

import android.content.Context
import com.example.taskhumanhometest.data.remote.SkillsApi
import com.example.taskhumanhometest.data.util.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**

Dagger module for providing app-wide dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
    Provides a singleton instance of Retrofit with a base URL and a JSON converter factory
    @return the default Retrofit instance
     */
    @Singleton
    @Provides
    fun provideDefaultRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api-devenv.taskhuman.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(getOkHttpClient())
        .build()

    /**
    Provides a singleton instance of OkHttpClient for making HTTP requests with logging
    @return an OkHttpClient instance with logging enabled
     */
    private fun getOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    /**
    Provides a singleton instance of SkillsApi for accessing the remote API
    @param retrofit the Retrofit instance used for API communication
    @return the SkillsApi instance for accessing the remote API
     */
    @Provides
    @Singleton
    fun provideSkillsApi(retrofit: Retrofit): SkillsApi {
        return retrofit.create(SkillsApi::class.java)
    }

    /**
    Provides a singleton instance of SessionManager for managing the user's session
    @param context the context used for accessing SharedPreferences
    @return the SessionManager instance for managing the user's session
     */
    @Provides
    @Singleton
    fun provideSessionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManager(context)
    }
}