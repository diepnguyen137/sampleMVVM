package com.skg.mybook

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.skg.mybook.common.BaseViewModelFactory
import com.skg.mybook.viewModel.ArticleSharedViewModel
import com.skg.mybook.viewModel.ArticleViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var sharedviewModel: ArticleSharedViewModel
    private lateinit var viewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedviewModel = this?.run {
            ViewModelProviders.of(this)[ArticleSharedViewModel::class.java]
        }

        viewModel = ViewModelProviders.of(this, BaseViewModelFactory { ArticleViewModel(application) })
            .get(ArticleViewModel::class.java)
        viewModel.getAllArticle()?.observe(this, Observer { articles ->
            sharedviewModel.setArticleList(articles)
        })
        bottom_navi_view.setOnNavigationItemSelectedListener { p0 ->
            when (p0.itemId) {
                R.id.homeFragment -> showHome()
                R.id.saveFragment -> showSave()
                R.id.nav_profile -> showProfile()
            }
            true
        }
    }

    private fun showProfile() {

    }

    private fun showSave() {
    }

    private fun showHome() {
    }
}
