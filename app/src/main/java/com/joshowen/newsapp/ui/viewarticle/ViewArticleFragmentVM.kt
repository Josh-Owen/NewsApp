package com.joshowen.newsapp.ui.viewarticle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joshowen.newsrepository.repos.NewsRepository
import com.joshowen.newsrepository.room.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class ViewArticleFragmentVM @Inject constructor(val newsRepository: NewsRepository): ViewModel() {


    private val articleChannel = Channel<Article>()
    private val articleFlow = articleChannel.receiveAsFlow()

    private val articleDescriptionChannel = Channel<String>()
    val articleDescriptionFlow = articleDescriptionChannel.receiveAsFlow()

    private val articleAuthorChannel = Channel<String>()
    val articleAuthorFlow = articleAuthorChannel.receiveAsFlow()

    private val articleTitleChannel = Channel<String>()
    val articleTitleFlow = articleTitleChannel.receiveAsFlow()

    private val articleContentChannel = Channel<String>()
    val articleContentFlow = articleContentChannel.receiveAsFlow()

    private val articleUrlChannel = Channel<String>()
    val articleUrlFlow = articleUrlChannel.receiveAsFlow()


    val articleStarredChannel = MutableSharedFlow<Boolean>()
   // private val articleStarredChannel = Channel<Boolean>()
//    val articleStarredFlow = articleStarredChannel.receiveAsFlow()


    init {
        viewModelScope.launch {
            articleFlow.collectLatest {
                articleDescriptionChannel.send(it.description ?: "")
                articleAuthorChannel.send(it.author ?: "")
                articleTitleChannel.send(it.title ?: "")
                articleContentChannel.send(it.content ?: "")
                articleUrlChannel.send(it.url ?: "")
                articleStarredChannel.tryEmit(it.isStarred)
              //  articleStarredChannel.send(it.isStarred)
            }
        }
    }

    suspend fun addArticle(article: Article) {
        articleChannel.send(article)
    }

    suspend fun starPressed() {
   //     articleStarredChannel.tryEmit(false)
        viewModelScope.launch {
            articleStarredChannel
                .collect { isStarred ->
                    articleStarredChannel.tryEmit(!isStarred)
                }
        }

        //viewModelScope.launch {
//            articleFlow.collectLatest {
//
//            }
        //}
    }
}

