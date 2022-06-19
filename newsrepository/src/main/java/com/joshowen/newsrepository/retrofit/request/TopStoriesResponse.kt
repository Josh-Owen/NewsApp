package com.joshowen.newsrepository.retrofit.request

import com.joshowen.newsrepository.data.Article


data class TopStoriesResponse(
    val status : String?,
    val totalResults : Int?,
    val articles: List<Article> = emptyList()
)
