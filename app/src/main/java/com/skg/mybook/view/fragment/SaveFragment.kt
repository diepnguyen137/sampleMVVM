package com.skg.mybook.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skg.mybook.R
import com.skg.mybook.common.BaseViewModelFactory
import com.skg.mybook.model.Article
import com.skg.mybook.view.adapter.ArticleAdapter
import com.skg.mybook.viewModel.ArticleViewModel
import com.skg.mybook.viewmodel.sharedViewModel.SaveDetailSharedViewModel
import kotlinx.android.synthetic.main.fragment_save.*
/**
 * A simple [Fragment] subclass.
 *
 */
class SaveFragment : Fragment(), ArticleAdapter.ItemClickListener {
    override fun onItemClicked(article: Article) {
        sharedviewModel.setArticle(article)
        view?.findNavController()?.navigate(R.id.action_save_to_detail)
    }
    private lateinit var viewModel: ArticleViewModel
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var sharedviewModel: SaveDetailSharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_save, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, BaseViewModelFactory {
            ArticleViewModel(activity!!)
        }).get(ArticleViewModel::class.java)
        sharedviewModel = activity?.run {
            ViewModelProviders.of(this)[SaveDetailSharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewManager = LinearLayoutManager(activity)
        viewModel.getSavedArticle()?.observe(this, Observer { articles ->
            articleAdapter = ArticleAdapter(articles, this)
            save_list.apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = articleAdapter
            }
        })
    }
}
