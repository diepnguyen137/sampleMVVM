package com.skg.mybook.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skg.mybook.model.Article
import com.skg.mybook.model.ArticleList
import com.skg.mybook.repository.ArticleRepository

class ArticleViewModel: ViewModel() {
    private var repository:ArticleRepository ?= null
    private var allArticles: MutableLiveData<ArticleList> ?= null
    private var mArticle:Article ? = null

    init {
        if(repository == null){
            repository = ArticleRepository()
        }
    }
    fun getAllArticle(): MutableLiveData<ArticleList>? {
        allArticles = repository!!.getAllArticle("us","business")
        return allArticles
    }
    fun setArticle(article: Article){
        mArticle = article
    }
    fun getArticle():Article {
        return mArticle!!
    }
}