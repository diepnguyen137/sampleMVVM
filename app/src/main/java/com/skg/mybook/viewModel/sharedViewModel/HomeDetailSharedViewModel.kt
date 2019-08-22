package com.skg.mybook.viewModel.sharedViewModel

import androidx.lifecycle.MutableLiveData
import com.skg.mybook.model.Article
import com.skg.mybook.model.ArticleList

class HomeDetailSharedViewModel: SharedViewModel(){
    private val mArticle = MutableLiveData<Article>()
    private val mArticleList = MutableLiveData<ArticleList>()

    override fun setArticle(article: Article) {
        mArticle.value = article
    }
    override fun getArticle(): MutableLiveData<Article> = mArticle
    override fun getArticleList(): MutableLiveData<ArticleList> = mArticleList
    override fun setArticleList(articleList: ArticleList) {
        mArticleList.value = articleList
    }

}