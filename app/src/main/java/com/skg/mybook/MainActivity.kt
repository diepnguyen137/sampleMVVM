package com.skg.mybook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.skg.mybook.view.ArticleViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var articleViewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)

        articleViewModel.getAllArticle()?.observe(this, Observer { articles ->
            Log.d(MainActivity::class.java.simpleName, "data: $articles")
        })

    }
}
