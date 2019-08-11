package com.skg.mybook.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skg.mybook.model.Article
import com.skg.mybook.model.ArticleList

class ArticleSharedViewModel: ViewModel(){
    private val mArticle = MutableLiveData<Article>()
    private val mArticleList = MutableLiveData<ArticleList>()

    fun setArticle(article: Article){
        mArticle?.value = article
    }
    fun getArticle():MutableLiveData<Article> {
        return mArticle!!
    }
    fun getArticleList():MutableLiveData<ArticleList>{
        return mArticleList
    }
    fun setArticleList(articleList: ArticleList){
        mArticleList.value = articleList
    }

}