package com.skg.mybook.viewModel.sharedViewModel

import androidx.lifecycle.MutableLiveData
import com.skg.mybook.model.Article
import com.skg.mybook.model.ArticleList

class SaveDetailSharedViewModel : SharedViewModel(){
    override fun getArticleList(): MutableLiveData<ArticleList> {
        return MutableLiveData()
    }

    override fun setArticleList(articleList: ArticleList) {
    }

    private val mArticle = MutableLiveData<Article>()
    override fun setArticle(article: Article) {
        mArticle.value = article
    }

    override fun getArticle(): MutableLiveData<Article> = mArticle
}