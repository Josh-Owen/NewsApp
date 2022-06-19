package com.joshowen.newsrepository.repos

import com.joshowen.newsrepository.utils.callApi
import com.joshowen.newsrepository.retrofit.request.TopStoriesResponse
import com.joshowen.newsrepository.retrofit.NewsService
import com.joshowen.newsrepository.retrofit.ResultWrapper
import com.joshowen.newsrepository.room.NewsDao
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(private val service : NewsService, private val newsDao : NewsDao): NewsRepository {

    override suspend fun getTopStories(isoCode: String): ResultWrapper<Response<TopStoriesResponse>> {
        return callApi {
            service.getTopHeadlines(isoCode)
        }
        // Todo: Map TopStoriesArticleResponse to Article & Handle different errors & Store in Room
//        service.getTopHeadlines(isoCode).body()?.articles?.forEach {
//            it.mapToArticle()
//        }
    }
}