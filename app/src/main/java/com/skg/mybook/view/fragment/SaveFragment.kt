package com.skg.mybook.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skg.mybook.R
import com.skg.mybook.model.Article
import com.skg.mybook.view.adapter.ArticleAdapter
import com.skg.mybook.viewModel.ArticleViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 *
 */
class SaveFragment : Fragment(), ArticleAdapter.ItemClickListener {
    override fun onItemClicked(article: Article) {

    }

    private lateinit var viewModel: ArticleViewModel
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_save, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =  ViewModelProviders.of(this)[ArticleViewModel::class.java]

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewManager = LinearLayoutManager(activity)
//        viewModel.getSavedArticle()?.observe(this, Observer { articles ->
//            articleAdapter = ArticleAdapter(articles, this)
//            save_list.apply {
//                setHasFixedSize(true)
//                layoutManager = viewManager
//                adapter = articleAdapter
//            }
//        })
    }
}
