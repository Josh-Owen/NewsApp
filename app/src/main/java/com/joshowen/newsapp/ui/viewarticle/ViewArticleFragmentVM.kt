package com.joshowen.newsapp.ui.viewarticle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joshowen.newsapp.ext.takeWhen
import com.joshowen.newsrepository.repos.NewsRepository
import com.joshowen.newsrepository.room.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
    class ViewArticleFragmentVM @Inject constructor(val newsRepository: NewsRepository): ViewModel() {


    //region Variables

    private val articleChannel = MutableStateFlow(Article())
    private val articleFlow = articleChannel.asStateFlow()

    private val articleDescriptionChannel = MutableStateFlow("")
    private val articleDescriptionFlow = articleDescriptionChannel.asStateFlow()

    private val articleAuthorChannel = MutableStateFlow("")
    private val articleAuthorFlow = articleAuthorChannel.asStateFlow()

    private val articleTitleChannel = MutableStateFlow("")
    private val articleTitleFlow = articleTitleChannel.asStateFlow()

    private val articleContentChannel = MutableStateFlow("")
    private val articleContentFlow = articleContentChannel.asStateFlow()

    private val articleUrlChannel = MutableStateFlow("")
    private val articleUrlFlow = articleUrlChannel.asStateFlow()

    private val clickedArticleChannel = Channel<Unit>()
    private val clickedArticleFlow = clickedArticleChannel.receiveAsFlow()

    private val articleStarredChannel = MutableStateFlow(false)
    private val articleStarredFlow = articleStarredChannel.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    private val isLoading = _isLoading.asStateFlow()

    private var articleClickedFlow: Flow<String> = articleUrlFlow.takeWhen(clickedArticleFlow)


    //endregion

    init {

        viewModelScope.launch {

            articleFlow
                .collectLatest {
                articleDescriptionChannel.value = it.description ?: ""
                articleAuthorChannel.value = it.author ?: ""
                articleTitleChannel.value = it.title ?: ""
                articleContentChannel.value = it.content ?: ""
                articleUrlChannel.value = it.url ?: ""
                articleStarredChannel.value = it.isStarred
                _isLoading.value = false
            }
        }
    }

    suspend fun selectedArticle(article: Article) {
        articleChannel.emit(article)
    }

    suspend fun clickedArticle() {
        clickedArticleChannel.send(Unit)
    }

    suspend fun starPressed() {
        /*
         Todo: Definitely a better way to do this, come back to it me and check out some of these extensions
           https://github.com/akarnokd/kotlin-flow-extensions
         */
        viewModelScope.launch {
            articleStarredFlow
                .take(1)
                .collectLatest {
                    articleStarredChannel.emit(!it)
                }
        }
    }

    fun getArticleDescription(): Flow<String> {
        return articleDescriptionFlow
    }

    fun getArticleContent(): Flow<String> {
        return articleContentFlow
    }

    fun getArticleAuthor(): Flow<String> {
        return articleAuthorFlow
    }

    fun getArticleTitle(): Flow<String> {
        return articleTitleFlow
    }

    fun articleClicked(): Flow<String> {
        return articleClickedFlow
    }

    fun getLoadingState() : Flow<Boolean> {
        return isLoading
    }

    fun getFlow(): Flow<Boolean> {

        return combine(articleFlow, articleStarredFlow) { article, starred ->
            article.isStarred = starred
            article
        }.mapLatest {
            val doesArticleExist = newsRepository.hasArticleWithId(it.id)
            if (doesArticleExist) {
                newsRepository.updateArticle(it)
            } else {
                newsRepository.insertArticle(it)
            }
            it.isStarred
        }
    }
}


