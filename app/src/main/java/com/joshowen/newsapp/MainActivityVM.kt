package com.joshowen.newsapp

import androidx.lifecycle.ViewModel
import com.joshowen.newsapp.config.DEFAULT_APPLICATION_LOCALE
import com.joshowen.newsrepository.data.Article
import com.joshowen.newsrepository.repos.NewsRepository
import com.joshowen.newsrepository.retrofit.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Suppress("MoveVariableDeclarationIntoWhen")
@HiltViewModel
class MainActivityVM @Inject constructor(private val newsRepo : NewsRepository): ViewModel() {

    suspend fun callAPI(): List<Article> = withContext(Dispatchers.IO) {
        val response = newsRepo.getTopStories(DEFAULT_APPLICATION_LOCALE) as ResultWrapper.Success
        response.value.body()?.articles ?: listOf()
    }

}