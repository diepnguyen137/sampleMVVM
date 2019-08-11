package com.skg.mybook.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skg.mybook.dao.ArticleDao
import com.skg.mybook.model.Article
import com.skg.mybook.model.ArticleList
import com.skg.mybook.service.ArticleService
import com.skg.mybook.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleRepository(articleDao: ArticleDao) {
    private var articleService: ArticleService? = null
    private var articleDao: ArticleDao = articleDao

    init {
        articleService = RetrofitService.create(ArticleService::class.java)
    }

    fun getAllArticle(country: String, category: String): MutableLiveData<ArticleList> {
        val data = MutableLiveData<ArticleList>()
        articleService?.getArticle(country, category)?.enqueue(object : Callback<ArticleList> {
            override fun onFailure(call: Call<ArticleList>, t: Throwable) {

            }

            override fun onResponse(call: Call<ArticleList>, response: Response<ArticleList>) {
                data.value = response.body()
            }
        })
        return data
    }

    /**
     * Add a wrapper for the insert() method. You must call this on a non-UI thread or your app will crash. Room ensures that you       don't do any long-running operations on the main thread, blocking the UI. Add the @WorkerThread annotation, to mark that        this method needs to be called from a non-UI thread. Add the suspend modifier to tell the compiler that this needs to be        called from a coroutine or another suspending function.
     * */
    @WorkerThread
    suspend fun saveAritcle(article: Article) {
        articleDao.insertArticle(article)
    }

    fun getSavedArticle(): LiveData<List<Article>> {
        return articleDao.getSavedArticle()
    }

}