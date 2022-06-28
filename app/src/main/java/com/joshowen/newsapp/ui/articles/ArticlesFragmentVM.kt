package com.joshowen.newsapp.ui.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.joshowen.newsapp.config.DEFAULT_APPLICATION_LOCALE
import com.joshowen.newsrepository.paging.PagingSource
import com.joshowen.newsrepository.repos.NewsRepository
import com.joshowen.newsrepository.retrofit.request.TopStoriesResponse
import com.joshowen.newsrepository.retrofit.wrappers.ResultWrapper
import com.joshowen.newsrepository.retrofit.request.mapToArticle
import com.joshowen.newsrepository.room.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ArticlesFragmentVM @Inject constructor(private val newsRepository: NewsRepository): ViewModel() {

    private val pager =
        Pager(config = PagingConfig(pageSize = 10, enablePlaceholders = true, initialLoadSize = 20),
            pagingSourceFactory = { PagingSource(newsRepository.getNewsService()) }
        ).flow.cachedIn(viewModelScope)

    fun getPager(): Flow<PagingData<Article>> {
        return pager
    }
}