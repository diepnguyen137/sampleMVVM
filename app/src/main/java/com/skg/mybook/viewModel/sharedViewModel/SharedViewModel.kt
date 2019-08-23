package com.skg.mybook.viewmodel.sharedViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skg.mybook.model.Article
import com.skg.mybook.model.ArticleList


abstract class  SharedViewModel: ViewModel() {
    abstract fun setArticle(article: Article)
    abstract fun getArticle(): MutableLiveData<Article>
    abstract fun getArticleList(): MutableLiveData<ArticleList>
    abstract fun setArticleList(articleList: ArticleList)
}