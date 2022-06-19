package com.joshowen.newsrepository.retrofit.request


data class TopStoriesResponse(
    val status : String?,
    val totalResults : Int?,
    val articles: List<TopStoriesArticleResponse> = emptyList()
)
