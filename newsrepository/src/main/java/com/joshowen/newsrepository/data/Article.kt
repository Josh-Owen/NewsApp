package com.joshowen.newsrepository.data

data class Article(
    val title : String?,
    val author : String?,
    val description : String,
    val url : String?,
    val urlToImage : String?,
    val publishedAt : String?,
    val content : String?,
)