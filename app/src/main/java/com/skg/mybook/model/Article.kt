package com.skg.mybook.model

import androidx.databinding.BindingAdapter

data class Article(
     var source: Source,
     var author: String,
     var title: String,
     var description:String,
     var urlToImage: String
)