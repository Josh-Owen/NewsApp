package com.joshowen.newsrepository.base

import com.joshowen.newsrepository.repos.NewsRepository
import com.joshowen.newsrepository.repos.NewsRepositoryImpl
import com.joshowen.newsrepository.retrofit.NewsService
import com.joshowen.newsrepository.room.NewsDao
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
open class BaseUnitTest {

    @Mock
    lateinit var newsService : NewsService

    @Mock
    lateinit var newsDao: NewsDao

    @Mock
    lateinit var newsRepository: NewsRepository

    open fun setup() {
       newsRepository = NewsRepositoryImpl(newsService, newsDao)
    }

}