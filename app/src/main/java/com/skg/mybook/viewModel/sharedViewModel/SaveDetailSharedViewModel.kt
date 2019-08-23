package com.skg.mybook.viewmodel.sharedViewModel

import androidx.lifecycle.MutableLiveData
import com.skg.mybook.model.Article
import com.skg.mybook.model.ArticleList

class SaveDetailSharedViewModel : SharedViewModel(){
    private val mArticle = MutableLiveData<Article>()
    override fun getArticleList(): MutableLiveData<ArticleList> {
        return MutableLiveData()
    }
    override fun setArticleList(articleList: ArticleList) {
    }
    override fun setArticle(article: Article) {
        mArticle.value = article
    }
    override fun getArticle(): MutableLiveData<Article> = mArticle
}