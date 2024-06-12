package com.ramirez.rickandmorty.di

import com.ramirez.rickandmorty.data.local.datasource.LocalCharacterDataSource
import com.ramirez.rickandmorty.data.local.datasource.impl.LocalCharacterDataSourceImpl
import com.ramirez.rickandmorty.data.remote.network.RemoteCharacterDataSource
import com.ramirez.rickandmorty.data.remote.network.RemoteCharacterDataSourceImpl
import com.ramirez.rickandmorty.domain.repositories.CharacterRepository
import com.ramirez.rickandmorty.domain.repositories.impl.CharacterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(remoteCharacterDataSourceImpl: RemoteCharacterDataSourceImpl): RemoteCharacterDataSource = remoteCharacterDataSourceImpl

    @Singleton
    @Provides
    fun provideLocalDataSource(localCharacterDataSourceImpl: LocalCharacterDataSourceImpl): LocalCharacterDataSource = localCharacterDataSourceImpl

    @Singleton
    @Provides
    fun provideRepository(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository = characterRepositoryImpl
}