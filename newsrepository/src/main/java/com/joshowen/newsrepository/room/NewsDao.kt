package com.joshowen.newsrepository.room

import androidx.paging.PagingSource
import androidx.room.*
import com.joshowen.newsrepository.room.NewsDatabase.Companion.DB_ARTICLES_TABLE_NAME
import com.joshowen.newsrepository.room.models.Article

@Dao
interface NewsDao {

    @Query("SELECT * FROM $DB_ARTICLES_TABLE_NAME ORDER BY id ASC")
    fun loadArticles(): PagingSource<Int, Article>

    @Query("SELECT * FROM $DB_ARTICLES_TABLE_NAME WHERE isStarred = true ORDER BY id ASC")
    fun loadStarredArticles(): PagingSource<Int, Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateArticle(article: Article)

    @Query("SELECT * FROM $DB_ARTICLES_TABLE_NAME WHERE id=:id LIMIT 1")
    suspend fun getArticleById(id: String): Article?

    @Query("DELETE FROM $DB_ARTICLES_TABLE_NAME WHERE id=:id")
    suspend fun deleteArticleById(id: Int)

}