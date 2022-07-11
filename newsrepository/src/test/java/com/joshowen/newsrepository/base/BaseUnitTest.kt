package com.joshowen.newsrepository.base

import com.joshowen.newsrepository.repos.NewsRepository
import com.joshowen.newsrepository.retrofit.NewsService
import com.joshowen.newsrepository.room.NewsDao
import com.joshowen.newsrepository.room.NewsDatabase
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
abstract class BaseUnitTest {

    @Mock
    lateinit var newsService : NewsService

    @Mock
    lateinit var newsDao : NewsDao

    @Mock
    lateinit var newsRepository: NewsRepository

    @Mock
    private lateinit var articlesDb: NewsDatabase


    @Before
    open fun setup() {
//        articlesDb = Room.inMemoryDatabaseBuilder(
//            ApplicationProvider.getApplicationContext(), NewsDatabase::class.java).build()
//        newsDao = articlesDb.newsDao()
   //     newsRepository = NewsRepositoryImpl(newsService, newsDao)
    }

//    @After
//    fun closeDb() {
//        articlesDb.close()
//    }


}