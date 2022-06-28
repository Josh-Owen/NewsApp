package com.joshowen.newsrepository.retrofit

import com.joshowen.newsrepository.retrofit.request.TopStoriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): Response<TopStoriesResponse>

    @GET("everything")
    suspend fun getEverything(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): Response<TopStoriesResponse>


}