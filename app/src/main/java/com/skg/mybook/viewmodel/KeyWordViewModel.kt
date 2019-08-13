package com.skg.mybook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skg.mybook.repository.KeyWordRepository

class KeyWordViewModel :ViewModel(){
    private var keyWordRepository: KeyWordRepository?= null

    init {
        keyWordRepository = KeyWordRepository()
    }

    fun getAllKeyWords():MutableLiveData<List<String>>{
        return keyWordRepository!!.getKeyWords()
    }
}