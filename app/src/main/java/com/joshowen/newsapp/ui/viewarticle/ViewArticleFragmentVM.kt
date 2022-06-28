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

@HiltViewModel
class ViewArticleFragmentVM @Inject constructor(val newsRepository: NewsRepository): ViewModel() {


    //region Variables

    private val articleChannel = Channel<Article>()
    private val articleFlow = articleChannel.receiveAsFlow()

    private val articleDescriptionChannel = Channel<String>()
    private val articleDescriptionFlow = articleDescriptionChannel.receiveAsFlow()

    private val articleAuthorChannel = Channel<String>()
    private val articleAuthorFlow = articleAuthorChannel.receiveAsFlow()

    private val articleTitleChannel = Channel<String>()
    private val articleTitleFlow = articleTitleChannel.receiveAsFlow()

    private val articleContentChannel = Channel<String>()
    private val articleContentFlow = articleContentChannel.receiveAsFlow()

    private val articleUrlChannel = Channel<String>()
    private val articleUrlFlow = articleUrlChannel.receiveAsFlow()

    private val clickedArticleChannel = Channel<Unit>()
    private val clickedArticleFlow = clickedArticleChannel.receiveAsFlow()


    private val articleStarredChannel = Channel<Unit>()
    private val articleStarredFlow = articleStarredChannel.receiveAsFlow()

    private var articleCLickedFlow : Flow<String> = articleUrlFlow.takeWhen(clickedArticleFlow)

    //endregion

//    private val articleStarredChannel = Channel<Boolean>()
//    val articleStarredFlow = articleStarredChannel.receiveAsFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    var addedFlow: Flow<Boolean> = articleFlow.takeWhen(articleStarredFlow)
        .mapLatest {  article  ->
            article.isStarred = !article.isStarred
            article
         }
        .map {
            newsRepository.toggleStarArticle(it)
        }


    init {


        viewModelScope.launch {

            articleFlow.collectLatest {
                articleDescriptionChannel.send(it.description ?: "")
                articleAuthorChannel.send(it.author ?: "")
                articleTitleChannel.send(it.title ?: "")
                articleContentChannel.send(it.content ?: "")
                articleUrlChannel.send(it.url ?: "")
//                articleStarredChannel.send(it.isStarred)
                //  articleStarredChannel.send(it.isStarred)
            }
        }
    }

    suspend fun selectedArticle(article: Article) {
        articleChannel.send(article)
    }

    suspend fun clickedArticle() {
        clickedArticleChannel.send(Unit)
    }

    suspend fun starPressed() {
        articleStarredChannel.send(Unit)
    }

    fun getArticleDescription() : Flow<String> {
        return articleDescriptionFlow
    }

    fun getArticleContent() : Flow<String> {
        return articleContentFlow
    }

    fun getArticleAuthor() : Flow<String> {
        return articleAuthorFlow
    }

    fun getArticleTitle() : Flow<String> {
        return articleTitleFlow
    }

    fun articleClicked() : Flow<String> {
        return articleCLickedFlow
    }
}


