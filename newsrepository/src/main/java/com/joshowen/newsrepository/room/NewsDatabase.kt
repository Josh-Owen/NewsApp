package com.joshowen.newsrepository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joshowen.newsrepository.room.models.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = true
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {

        //region Variables
        const val DB_NAME = "NEWS_DATABASE"
        const val DB_ARTICLES_TABLE_NAME = "ARTICLES"

        @Volatile
        private var INSTANCE: NewsDatabase? = null
        //endregion

        fun getInstance(context: Context): NewsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NewsDatabase::class.java,
                        DB_NAME
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}