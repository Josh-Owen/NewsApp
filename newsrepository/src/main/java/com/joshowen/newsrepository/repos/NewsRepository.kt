package com.joshowen.newsrepository.repos

import com.joshowen.newsrepository.request.TopStoriesResponse
import com.joshowen.newsrepository.utils.ResultWrapper
import retrofit2.Response

interface NewsRepository {
    suspend fun getTopStories(isoCode : String) : ResultWrapper<Response<TopStoriesResponse>>
}