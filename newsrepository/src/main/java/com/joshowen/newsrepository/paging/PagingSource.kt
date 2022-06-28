package com.joshowen.newsrepository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joshowen.newsrepository.ARTICLE_SEARCH_QUERY
import com.joshowen.newsrepository.PAGINATION_PAGE_SIZE
import com.joshowen.newsrepository.STARTING_PAGE_INDEX
import com.joshowen.newsrepository.retrofit.NewsService
import com.joshowen.newsrepository.retrofit.request.mapToArticle
import com.joshowen.newsrepository.room.models.Article
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject



class PagingSource @Inject constructor( private val newsService: NewsService) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {

            val startingPageNumber = params.key ?: STARTING_PAGE_INDEX

            val response = newsService.getStoriesByTopicPaginated(ARTICLE_SEARCH_QUERY, startingPageNumber, PAGINATION_PAGE_SIZE)

            val prevKey = if (startingPageNumber == 1) null else startingPageNumber-1
            val nextKey = startingPageNumber+1

            val mappedArticles = response.body()?.articles?.map {
                it.mapToArticle()
            } ?: listOf()

            LoadResult.Page(
                data = mappedArticles,
                prevKey = prevKey,
                nextKey = if(nextKey <= 10) nextKey else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}