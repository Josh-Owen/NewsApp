package com.joshowen.newsapp.ui.starred

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.joshowen.newsapp.config.DEFAULT_PAGING_LOAD_SIZE
import com.joshowen.newsapp.config.DEFAULT_PAGING_PAGE_SIZE
import com.joshowen.newsapp.config.PAGING_PLACEHOLDERS_ENABLED_BY_DEFAULT
import com.joshowen.newsrepository.repos.NewsRepository
import com.joshowen.newsrepository.room.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class StarredFragmentVM @Inject constructor(private val newsRepository: NewsRepository): ViewModel() {

    private val pager = Pager(
        PagingConfig(
            pageSize = DEFAULT_PAGING_PAGE_SIZE,
            enablePlaceholders = PAGING_PLACEHOLDERS_ENABLED_BY_DEFAULT,
            initialLoadSize = DEFAULT_PAGING_LOAD_SIZE
        )
    ) {
        newsRepository.getStarredStories()
    }.flow.cachedIn(viewModelScope)

    fun getStarredArticles(): Flow<PagingData<Article>> {
        return pager
    }
}