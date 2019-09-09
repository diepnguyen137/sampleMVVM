package com.skg.mybook.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skg.mybook.R
import com.skg.mybook.common.BaseViewModelFactory
import com.skg.mybook.model.Article
import com.skg.mybook.util.Constant
import com.skg.mybook.view.adapter.ArticleAdapter
import com.skg.mybook.viewModel.sharedViewModel.HomeDetailViewModel
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(), ArticleAdapter.ItemClickListener {

    override fun onItemClicked(position:Int) {
        sharedviewModel.getItemByIndex(position)
        val screenName = Constant.HOME
        var bundle = bundleOf(Constant.SCREEN_NAME to screenName)
        view?.findNavController()?.navigate(R.id.gotoDetail,bundle)
    }
    private lateinit var sharedviewModel: HomeDetailViewModel
    private lateinit var articleAdapter : ArticleAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**init ViewModel*/
        sharedviewModel = activity?.run {
            ViewModelProviders.of(this, BaseViewModelFactory {
                HomeDetailViewModel(context!!)
            }).get(HomeDetailViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewManager = LinearLayoutManager(activity)
        articleAdapter = ArticleAdapter(this)
        home_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = articleAdapter
        }

        sharedviewModel.mArticleList.observe(this, Observer { articles ->
            if(articles != null){
                articleAdapter.setAdapter(articles)
                if(articles.isEmpty()){
                    no_content.visibility = View.VISIBLE
                }
                else{
                    no_content.visibility = View.GONE
                }
            }
            else{
                articleAdapter.resetAdapter()
                if(no_content.isVisible){
                    no_content.visibility = View.GONE
                }
            }
        })

        sharedviewModel.isLoading.observe(this, Observer { value ->
            if(!value){
                progress_bar.visibility = View.GONE
            }
            else{
                progress_bar.visibility = View.VISIBLE
            }
        })
        search_view.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
//                sharedviewModel.updateList(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                /**Fragment return from back stack it executes this callback
                 *  so we need to stop it */
                if(search_view.isIconified || !isVisible){
                    return true
                }
                sharedviewModel.updateList(newText)
                return true
            }

        })
    }

}

