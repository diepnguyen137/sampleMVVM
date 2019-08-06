package com.skg.mybook.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
        private const val API_KEY = "e2c2131da49d443480e115d6b3b1d171"
        private var httpClient = OkHttpClient.Builder().addInterceptor(object : Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val request: Request = original.newBuilder()
                    .header("X-Api-Key",API_KEY).build()
                return chain.proceed(request)
            }
        })
        private val client = httpClient.build()

        fun <S> create(serviceClass: Class<S>): S {
            val retrofit = Retrofit.Builder()
                 .addConverterFactory(GsonConverterFactory.create())
                 .baseUrl(BASE_URL)
                 .client(client)
                 .build()
            return retrofit.create(serviceClass)
        }
    }
}




