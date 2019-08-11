package com.skg.mybook.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skg.mybook.model.Article
import com.skg.mybook.model.ArticleList
import com.skg.mybook.repository.ArticleRepository
import com.skg.mybook.room.ArticleRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleViewModel(context: Context) : ViewModel() {
    private var repository: ArticleRepository? = null
    private var allArticles: MutableLiveData<ArticleList>? = null

    init {
        if (repository == null) {
            val dao = ArticleRoom.getDatabase(context).articleDao()
            repository = ArticleRepository(dao)
        }

    }

    fun getAllArticle(): MutableLiveData<ArticleList>? {
        allArticles = repository!!.getAllArticle("us", "business")
        return allArticles
    }

    fun getSavedArticle(): LiveData<List<Article>>? {
        return repository!!.getSavedArticle()
    }
    /**
     * We want the insert() method to be called away from the main thread, so we're launching a new coroutine, based on the             coroutine scope defined previously. Because we're doing a database operation, we're using the IO Dispatcher.
     */

    fun saveArticle(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        repository?.saveAritcle(article)
    }
}