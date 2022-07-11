package com.joshowen.newsrepository.repos

import androidx.annotation.WorkerThread
import androidx.paging.PagingSource
import com.joshowen.newsrepository.retrofit.request.TopStoriesResponse
import com.joshowen.newsrepository.retrofit.NewsService
import com.joshowen.newsrepository.retrofit.wrappers.ResultWrapper
import com.joshowen.newsrepository.room.NewsDao
import com.joshowen.newsrepository.room.models.Article
import com.joshowen.newsrepository.utils.callApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(private val newsService : NewsService, private val newsDao : NewsDao): NewsRepository {

    @WorkerThread
    override suspend fun getAllStoriesPaginated(query: String, page : Int, pageSize : Int): ResultWrapper<Response<TopStoriesResponse>> {
        return callApi {
            newsService.getStoriesByTopicPaginated(query, page, pageSize)
        }
    }

    @WorkerThread
    override fun getStarredStories(): PagingSource<Int, Article> {
        return newsDao.loadStarredArticles()
    }

    @WorkerThread
    override fun getStories(): PagingSource<Int, Article> {
        return newsDao.loadArticles()
    }

    @WorkerThread
    override suspend fun updateArticle(article: Article) {
        newsDao.updateArticle(article)
    }

    @WorkerThread
    override suspend fun insertArticle(article: Article) {
        newsDao.insertArticle(article)
    }

    @WorkerThread
    override suspend fun hasArticleWithId(id: String) : Boolean {
        return newsDao.getArticleById(id) != null
    }

    override fun getNewsService(): NewsService {
        return newsService
    }
}