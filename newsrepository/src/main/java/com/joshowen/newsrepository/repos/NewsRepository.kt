package com.joshowen.newsrepository.repos

import androidx.paging.PagingSource
import com.joshowen.newsrepository.retrofit.NewsService
import com.joshowen.newsrepository.retrofit.request.TopStoriesResponse
import com.joshowen.newsrepository.retrofit.wrappers.ResultWrapper
import com.joshowen.newsrepository.room.models.Article
import retrofit2.Response

interface NewsRepository {
     fun getStarredStories(): PagingSource<Int, Article>
     fun getStories(): PagingSource<Int, Article>
     fun getNewsService(): NewsService
     suspend fun getAllStoriesPaginated(
          query: String,
          page: Int,
          pageSize: Int
     ): ResultWrapper<Response<TopStoriesResponse>>
     suspend fun updateArticle(article: Article)
     suspend fun insertArticle(article: Article)
     suspend fun hasArticleWithId(id: String): Boolean
}