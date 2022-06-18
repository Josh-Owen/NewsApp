package com.joshowen.newsrepository.retrofit

import com.joshowen.newsrepository.request.TopStoriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country : String): Response<TopStoriesResponse>

}