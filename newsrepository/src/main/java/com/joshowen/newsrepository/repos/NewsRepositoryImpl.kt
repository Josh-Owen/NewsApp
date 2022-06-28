package com.joshowen.newsrepository.repos

import androidx.annotation.WorkerThread
import androidx.paging.PagingSource
import com.joshowen.newsrepository.retrofit.request.TopStoriesResponse
import com.joshowen.newsrepository.retrofit.NewsService
import com.joshowen.newsrepository.retrofit.wrappers.ResultWrapper
import com.joshowen.newsrepository.room.NewsDao
import com.joshowen.newsrepository.room.models.Article
import com.joshowen.newsrepository.utils.callApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(private val newsService : NewsService, private val newsDao : NewsDao): NewsRepository {

    @WorkerThread
    override suspend fun getAllStoriesPaginated(query: String, page : Int, pageSize : Int): ResultWrapper<Response<TopStoriesResponse>> {
        return callApi {
            newsService.getEverything(query, page, pageSize)
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
    override suspend fun toggleStarArticle(article: Article) : Boolean {

        val previousEntry = newsDao.getArticleById(article.id)

        return if(previousEntry == null) {
            val updatedArticle = article.apply {
                isStarred = !isStarred
            }
            newsDao.insertArticle(updatedArticle)
            true
        }
        else {
            val updatedArticle = previousEntry.apply {
                isStarred = !isStarred
            }
            newsDao.updateArticle(updatedArticle)
            updatedArticle.isStarred
        }
    }

    override fun getNewsService(): NewsService {
        return newsService
    }
    @WorkerThread
    override fun getArticleUpdates(id : Int) : Flow<Article?> {
        return newsDao.getArticleChanges(id)
    }
}