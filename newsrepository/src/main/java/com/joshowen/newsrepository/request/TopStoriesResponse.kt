package com.joshowen.newsrepository.request

import com.joshowen.newsrepository.data.Article


data class TopStoriesResponse(
    val status : String?,
    val totalResults : Int?,
    val articles: List<Article> = emptyList()
)
