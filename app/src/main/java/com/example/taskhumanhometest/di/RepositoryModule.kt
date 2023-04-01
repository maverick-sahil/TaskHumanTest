package com.example.taskhumanhometest.di

import com.example.taskhumanhometest.data.repository.DefaultSkillRepository
import com.example.taskhumanhometest.domain.repository.SkillRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Sahil Salgotra on 01/04/23 12:48 PM
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindSkillsRepository(defaultSkillRepository: DefaultSkillRepository): SkillRepository
}
