package com.skg.mybook.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.skg.mybook.model.Article

@Dao
interface ArticleDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(vararg article: Article)

    @Query("SELECT * from article_table")
    fun getSavedArticle(): LiveData<List<Article>>
}