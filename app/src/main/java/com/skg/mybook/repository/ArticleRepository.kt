package com.skg.mybook.repository

import android.util.MutableDouble
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skg.mybook.dao.ArticleDao
import com.skg.mybook.model.Article
import com.skg.mybook.service.ArticleService
import com.skg.mybook.service.RetrofitService
import java.util.*
import kotlin.collections.ArrayList

class ArticleRepository(articleDao: ArticleDao) : BaseRepository() {
    private var articleService: ArticleService? = null
    private var articleDao: ArticleDao = articleDao

    init {
        articleService = RetrofitService.create(ArticleService::class.java)
    }

    /** Add the @WorkerThread annotation, to mark that
    * this method needs to be called from a non-UI thread.**/
    @WorkerThread
    suspend fun getAllArticle(country: String, category: String): ArrayList<Article> {
        var result = ArrayList<Article>()
        try {
            val response = articleService?.getArticle(country, category)!!
            if (response.isSuccessful) {
                result.addAll(response.body()!!.articles!!)
            }
        } catch (e: Exception) {

        }
        return result

    }

    /**
     * Add a wrapper for the insert() method.
     * You must call this on a non-UI thread or your app will crash.
     * Room ensures that you don't do any long-running operations on the main thread, blocking the UI.
     * Add the @WorkerThread annotation, to mark that
     * this method needs to be called from a non-UI thread.
     * Add the suspend modifier to tell the compiler that this needs to be
     * called from a coroutine or another suspending function.
     * */
    @WorkerThread
    suspend fun saveAritcle(article: Article) {
        articleDao.insertArticle(article)
    }

    fun getSavedArticle(): LiveData<List<Article>> {
        return articleDao.getSavedArticle()
    }

}