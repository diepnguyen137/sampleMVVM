package com.skg.mybook.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skg.mybook.R
import com.skg.mybook.view.adapter.ArticleAdapter
import com.skg.mybook.viewModel.ArticleViewModel
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: ArticleViewModel
    private lateinit var adapter : ArticleAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewManager = LinearLayoutManager(activity)
        viewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)
        home_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = this.adapter
        }
        viewModel.getAllArticle()?.observe(this, Observer { articles ->
            adapter = ArticleAdapter(activity!!,articles.articles)
        })
    }

}