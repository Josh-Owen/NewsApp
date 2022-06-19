package com.joshowen.newsrepository.room

import androidx.paging.PagingSource
import androidx.room.*
import com.joshowen.newsrepository.retrofit.request.TopStoriesArticleResponse
import com.joshowen.newsrepository.room.NewsDatabase.Companion.DB_ARTICLES_TABLE_NAME
import com.joshowen.newsrepository.room.NewsDatabase.Companion.DB_NAME
import com.joshowen.newsrepository.room.models.Article

@Dao
interface NewsDao {

    @Query("SELECT * FROM $DB_ARTICLES_TABLE_NAME ORDER BY id ASC")
    fun loadArticles(): PagingSource<Int, Article>

    @Query("SELECT * FROM $DB_ARTICLES_TABLE_NAME WHERE isStarred = true ORDER BY id ASC")
    fun loadStarredArticles(): PagingSource<Int, Article>

    @Update
    fun updateArticle(article : Article)

    @Insert
    fun addArticles(articles : List<Article>)

}