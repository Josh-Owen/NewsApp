package com.joshowen.newsrepository.di

import android.content.Context
import androidx.room.Room
import com.joshowen.newsrepository.room.NewsDao
import com.joshowen.newsrepository.room.NewsDatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NewsDatabaseModule {

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext appContext: Context): NewsDatabase {
        return NewsDatabase.getInstance(appContext)
    }

    @Provides
    @Singleton
    fun provideNewsDao(@ApplicationContext appContext: Context) : NewsDao {
       return NewsDatabase.getInstance(appContext).newsDao()
    }

}