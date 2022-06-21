package com.joshowen.newsapp

import androidx.lifecycle.ViewModel
import androidx.paging.PagingSource
import com.joshowen.newsapp.config.DEFAULT_APPLICATION_LOCALE
import com.joshowen.newsrepository.retrofit.request.TopStoriesArticleResponse
import com.joshowen.newsrepository.repos.NewsRepository
import com.joshowen.newsrepository.retrofit.ResultWrapper
import com.joshowen.newsrepository.room.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Suppress("MoveVariableDeclarationIntoWhen")
@HiltViewModel
class MainActivityVM @Inject constructor(private val newsRepo : NewsRepository): ViewModel() {

    suspend fun callAPI(): List<TopStoriesArticleResponse> = withContext(Dispatchers.IO) {
        val response = newsRepo.getTopStories(DEFAULT_APPLICATION_LOCALE) as ResultWrapper.Success
        response.value.body()?.articles ?: listOf()
    }

}