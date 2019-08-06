package com.skg.mybook.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skg.mybook.model.ArticleList
import com.skg.mybook.repository.ArticleRepository

class ArticleViewModel: ViewModel() {
    private var repository:ArticleRepository ?= null
    private var allArticles: MutableLiveData<ArticleList> ?= null

    init {
        if(repository == null){
            repository = ArticleRepository()
            allArticles = repository!!.getAllArticle("us","business")
        }
    }

    fun getAllArticle(): MutableLiveData<ArticleList>? {
        return allArticles
    }
}