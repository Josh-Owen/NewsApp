package com.joshowen.newsapp.ui.starred

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.joshowen.newsrepository.repos.NewsRepository
import com.joshowen.newsrepository.room.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class StarredFragmentVM @Inject constructor(private val newsRepository: NewsRepository): ViewModel() {

    private val pager = Pager(
        PagingConfig(pageSize = 10)
    ) {
        newsRepository.getStarredStories()
    }.flow.cachedIn(viewModelScope)

    fun getStarredArticles(): Flow<PagingData<Article>> {
        return pager
    }
}