package com.joshowen.newsapp.ui.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.joshowen.newsapp.config.DEFAULT_PAGING_LOAD_SIZE
import com.joshowen.newsapp.config.DEFAULT_PAGING_PAGE_SIZE
import com.joshowen.newsapp.config.PAGING_PLACEHOLDERS_ENABLED_BY_DEFAULT
import com.joshowen.newsrepository.paging.PagingSource
import com.joshowen.newsrepository.repos.NewsRepository
import com.joshowen.newsrepository.room.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ArticlesFragmentVM @Inject constructor(private val newsRepository: NewsRepository): ViewModel() {

    private val pager =
        Pager(config = PagingConfig(
            pageSize = DEFAULT_PAGING_PAGE_SIZE,
            enablePlaceholders = PAGING_PLACEHOLDERS_ENABLED_BY_DEFAULT,
            initialLoadSize = DEFAULT_PAGING_LOAD_SIZE
        ),
            pagingSourceFactory = { PagingSource(newsRepository.getNewsService()) }
        ).flow.cachedIn(viewModelScope)

    fun getArticles(): Flow<PagingData<Article>> {
        return pager
    }
}