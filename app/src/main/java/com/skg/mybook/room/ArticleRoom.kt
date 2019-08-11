package com.skg.mybook.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.skg.mybook.dao.ArticleDao
import com.skg.mybook.model.Article

@Database(entities = [Article::class], version = 2)
abstract class ArticleRoom: RoomDatabase(){
    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleRoom? = null

        fun getDatabase(context: Context): ArticleRoom {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleRoom::class.java,
                    "article_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}