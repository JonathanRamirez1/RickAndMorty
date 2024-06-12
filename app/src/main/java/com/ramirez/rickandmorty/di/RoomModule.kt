package com.ramirez.rickandmorty.di

import android.content.Context
import androidx.room.Room
import com.ramirez.rickandmorty.data.local.database.AppDatabase
import com.ramirez.rickandmorty.presentation.utils.Constants.APP_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideAppDatabase(appDatabase: AppDatabase) = appDatabase.characterDao()
}