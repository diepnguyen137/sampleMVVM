package com.skg.mybook.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.skg.mybook.service.KeyWordService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KeyWordRepository {
    private var keyWordService: KeyWordService? = null
    private  val BASE_URL = "https://raw.githubusercontent.com/tikivn/android-home-test/v2/"

    init {
        keyWordService = create()
    }
    private fun create(): KeyWordService {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    return retrofit.create(KeyWordService::class.java)
}
    fun getKeyWords(): MutableLiveData<List<String>> {
        val data = MutableLiveData<List<String>>()
        keyWordService?.getKeyWords()?.enqueue(object : Callback<List<String>> {
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.e(KeyWordService::class.java.simpleName, "Cannot fetch data")
            }
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                data.value = response.body()
            }
        })
        return data
    }
}