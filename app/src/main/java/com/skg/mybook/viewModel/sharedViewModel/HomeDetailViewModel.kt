package com.skg.mybook.viewModel.sharedViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skg.mybook.model.Article
import com.skg.mybook.repository.ArticleRepository
import com.skg.mybook.room.ArticleRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeDetailViewModel(context: Context): ViewModel(){
    private var repository: ArticleRepository? = null

    /**States && Signal**/
    var mArticleList =  MutableLiveData<ArrayList<Article>>()
    var mArticle = MutableLiveData<Article>()
    var isLoading = MutableLiveData<Boolean>()
    var isLoadMore = MutableLiveData<Boolean>()

    private var _currentPage = 0
    private var keyword = "business"

    init {
        if (repository == null) {
            val dao = ArticleRoom.getDatabase(context).articleDao()
            repository = ArticleRepository(dao)
            updateList(keyword)
        }
    }

    /**Trigger*/
    fun updateList(keyword: String?){
        reset()
        viewModelScope.launch {
            delay(1000)
            loadMore(keyword)
        }
    }

    private fun reset() {
        mArticleList.value = null
        isLoading.value = true
        _currentPage = 0
    }

    private suspend fun loadMore(keyword: String?) {
        _currentPage ++
        mArticleList.value = keyword?.let { repository!!.getAllArticle("us", it) }
        isLoading.value = false
    }

    fun getItemByIndex(index: Int){
        mArticle.value = mArticleList.value?.get(index)
    }

}