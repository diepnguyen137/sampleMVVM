package com.skg.mybook.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_table")
data class Article(
     @Embedded
     var source: Source?,
     var author: String?,
     var title: String?,
     var description:String?,
     var urlToImage: String?
){
     @PrimaryKey(autoGenerate = true)
     var articleID: Int = 0
}