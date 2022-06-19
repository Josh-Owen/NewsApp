package com.joshowen.newsrepository.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class Article (

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var title : String? = null,
    var author : String? = null,
    var description : String? = null,
    var url : String? = null,
    var urlToImage : String? = null,
    var publishedAt : String? = null,
    var content : String? = null,
    var isStarred : Boolean = false
)