package com.joshowen.newsrepository.retrofit

import com.joshowen.newsrepository.retrofit.request.TopStoriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("everything")
    suspend fun getStoriesByTopicPaginated(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): Response<TopStoriesResponse>
}