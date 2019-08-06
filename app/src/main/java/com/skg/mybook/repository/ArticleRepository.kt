package com.skg.mybook.repository

import androidx.lifecycle.MutableLiveData
import com.skg.mybook.model.ArticleList
import com.skg.mybook.service.ArticleService
import com.skg.mybook.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleRepository {
    private var articleService: ArticleService ? = null
    init {
        articleService = RetrofitService.create(ArticleService::class.java)
    }
    fun getAllArticle(country: String, category:String): MutableLiveData<ArticleList>{
        val data = MutableLiveData<ArticleList>()
        articleService?.getArticle(country,category)?.enqueue(object : Callback<ArticleList>{
            override fun onFailure(call: Call<ArticleList>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onResponse(call: Call<ArticleList>, response: Response<ArticleList>) {
                data.value = response.body()
            }
        })
        return data
    }


}