package com.skg.mybook.model

data class Article(
    private val source: Source,
    private val author: String,
    private val title: String,
    private val urlToImage: String

)