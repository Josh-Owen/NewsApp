package com.joshowen.newsrepository.repos

import androidx.paging.PagingSource
import com.joshowen.newsrepository.retrofit.NewsService
import com.joshowen.newsrepository.retrofit.request.TopStoriesResponse
import com.joshowen.newsrepository.retrofit.wrappers.ResultWrapper
import com.joshowen.newsrepository.room.models.Article
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NewsRepository {
     fun getStarredStories() : PagingSource<Int, Article>
     fun getStories() : PagingSource<Int, Article>
     suspend fun toggleStarArticle(article: Article) : Boolean
     fun getNewsService() : NewsService
     suspend fun getAllStoriesPaginated(query: String, page : Int, pageSize : Int): ResultWrapper<Response<TopStoriesResponse>>
     fun getArticleUpdates(id : Int) : Flow<Article?>
}