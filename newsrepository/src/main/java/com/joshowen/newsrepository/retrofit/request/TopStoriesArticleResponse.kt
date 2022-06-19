package com.joshowen.newsrepository.retrofit.request

import com.joshowen.newsrepository.room.models.Article

data class TopStoriesArticleResponse(
    val title : String?,
    val author : String?,
    val description : String,
    val url : String?,
    val urlToImage : String?,
    val publishedAt : String?,
    val content : String?,
)

fun TopStoriesArticleResponse.mapToArticle() : Article {
    return Article().apply {
        title = this@mapToArticle.title
        author = this@mapToArticle.author
        description = this@mapToArticle.description
        url = this@mapToArticle.url
        urlToImage = this@mapToArticle.urlToImage
        publishedAt = this@mapToArticle.publishedAt
        content = this@mapToArticle.content
    }
}