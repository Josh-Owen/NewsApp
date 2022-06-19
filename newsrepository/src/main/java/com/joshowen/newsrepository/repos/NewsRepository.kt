package com.joshowen.newsrepository.repos

import com.joshowen.newsrepository.retrofit.request.TopStoriesResponse
import com.joshowen.newsrepository.retrofit.ResultWrapper
import retrofit2.Response

interface NewsRepository {
    suspend fun getTopStories(isoCode : String) : ResultWrapper<Response<TopStoriesResponse>>
}