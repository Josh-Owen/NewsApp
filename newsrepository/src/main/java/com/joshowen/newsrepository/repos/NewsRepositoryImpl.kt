package com.joshowen.newsrepository.repos

import com.joshowen.newsrepository.utils.callApi
import com.joshowen.newsrepository.request.TopStoriesResponse
import com.joshowen.newsrepository.retrofit.NewsService
import com.joshowen.newsrepository.utils.ResultWrapper
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(private val service : NewsService): NewsRepository {

    override suspend fun getTopStories(isoCode: String): ResultWrapper<Response<TopStoriesResponse>> {
        return callApi {
            service.getTopHeadlines(isoCode)
        }
    }
}