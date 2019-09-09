package com.skg.mybook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.skg.mybook.common.BaseViewModelFactory
import com.skg.mybook.util.setupWithNavController
import com.skg.mybook.viewModel.sharedViewModel.HomeDetailViewModel
import com.skg.mybook.viewModel.ArticleViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var sharedviewModel: HomeDetailViewModel
    private lateinit var viewModel: ArticleViewModel
    private var currentNavController: LiveData<NavController>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, BaseViewModelFactory {
            ArticleViewModel(application)
        }).get(ArticleViewModel::class.java)

//        viewModel.getAllArticle()?.observe(this, Observer { articles ->
//            sharedviewModel.setArticleList(articles)
//        })
        if (savedInstanceState == null) {
            setUpBottomNavigation()
        } // Else, need to wait for onRestoreInstanceState
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        setUpBottomNavigation()
    }
    private fun setUpBottomNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navi_view)
        val graphIDs = listOf(R.navigation.home,R.navigation.save, R.navigation.profile)
        val controller= bottomNavigationView.setupWithNavController(
            navGraphIds = graphIDs,
            fragmentManager = supportFragmentManager,
            containerId = R.id.main_container,
            intent = intent
            )
        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}
