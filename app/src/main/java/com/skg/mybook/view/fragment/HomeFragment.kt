package com.skg.mybook.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skg.mybook.R
import com.skg.mybook.model.Article
import com.skg.mybook.utils.Constant
import com.skg.mybook.view.adapter.ArticleAdapter
import com.skg.mybook.view.adapter.KeyWordAdapter
import com.skg.mybook.viewModel.sharedViewModel.HomeDetailSharedViewModel
import com.skg.mybook.viewmodel.KeyWordViewModel
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(), ArticleAdapter.ItemClickListener {
    override fun onItemClicked(article: Article) {
        sharedviewModel.setArticle(article)
        val screenName = Constant.HOME
        var bundle = bundleOf(Constant.SCREEN_NAME to screenName)
        view?.findNavController()?.navigate(R.id.gotoDetail,bundle)
    }
    private lateinit var sharedviewModel: HomeDetailSharedViewModel
    private lateinit var keyWordviewModel: KeyWordViewModel

    private lateinit var articleAdapter : ArticleAdapter
    private lateinit var keyWordAdapter: KeyWordAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        keyWordviewModel = ViewModelProviders.of(this)[KeyWordViewModel::class.java]
        keyWordAdapter = KeyWordAdapter(activity!!)
        sharedviewModel = activity?.run {
            ViewModelProviders.of(this)[HomeDetailSharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        keyword_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                activity!!,
                LinearLayout.HORIZONTAL, false
            )
            adapter  = keyWordAdapter
        }
        keyWordviewModel.getAllKeyWords().observe(this, Observer<List<String>> { keywords ->
            keyWordAdapter.setAdapter(keywords)
        })
        viewManager = LinearLayoutManager(activity)
        sharedviewModel.getArticleList()?.observe(this, Observer { articles ->
            articleAdapter = ArticleAdapter(articles.articles,this)
            home_list.apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = articleAdapter
            }
        })
    }
}

