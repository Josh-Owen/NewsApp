package com.joshowen.newsapp.ui.starred

import androidx.lifecycle.ViewModel
import com.joshowen.newsapp.config.DEFAULT_APPLICATION_LOCALE
import com.joshowen.newsrepository.repos.NewsRepository
import com.joshowen.newsrepository.retrofit.ResultWrapper
import com.joshowen.newsrepository.retrofit.request.TopStoriesArticleResponse
import com.joshowen.newsrepository.room.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StarredFragmentVM @Inject constructor(private val newsRepository: NewsRepository): ViewModel() {

    init {

    }

    suspend fun callAPI(): List<Article> = withContext(Dispatchers.IO) {
        return@withContext newsRepository.getStarredStories()
    }

}