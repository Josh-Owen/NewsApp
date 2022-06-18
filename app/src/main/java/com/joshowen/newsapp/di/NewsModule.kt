package com.joshowen.newsapp.di

import com.joshowen.newsrepository.repos.NewsRepository
import com.joshowen.newsrepository.repos.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface NewsModule {

    @Binds
    @ViewModelScoped
    fun getNewsRepositoryImpl(repository: NewsRepositoryImpl): NewsRepository

}