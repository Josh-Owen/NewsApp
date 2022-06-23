package com.joshowen.newsrepository.repos

import com.joshowen.newsrepository.retrofit.request.TopStoriesResponse
import com.joshowen.newsrepository.retrofit.ResultWrapper
import com.joshowen.newsrepository.room.models.Article
import retrofit2.Response

interface NewsRepository {
    suspend fun getTopStories(isoCode : String) : ResultWrapper<Response<TopStoriesResponse>>
    suspend fun getStarredStories() : List<Article>
}