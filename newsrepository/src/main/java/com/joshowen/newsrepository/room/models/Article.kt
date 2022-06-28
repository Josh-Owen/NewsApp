package com.joshowen.newsrepository.room.models


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*


@Parcelize
@Entity(tableName = "articles")
data class Article (

    @PrimaryKey
    var id : String = "",

    var title : String? = null,
    var author : String? = null,
    var description : String? = null,
    var url : String? = null,
    var urlToImage : String? = null,
    var publishedAt : String? = null,
    var content : String? = null,
    var isStarred : Boolean = false
) : Parcelable