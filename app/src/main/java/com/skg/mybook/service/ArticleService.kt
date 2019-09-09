package com.skg.mybook.service

import com.skg.mybook.model.ArticleList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("top-headlines")
    suspend fun getArticle(@Query("country") country: String, @Query("category") category:String)
            : Response<ArticleList>
}