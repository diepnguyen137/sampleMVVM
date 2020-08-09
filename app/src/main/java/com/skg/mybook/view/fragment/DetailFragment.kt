package com.skg.mybook.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.skg.mybook.R
import com.skg.mybook.common.BaseViewModelFactory
import com.skg.mybook.databinding.FragmentDetailBinding
import com.skg.mybook.model.Article
import com.skg.mybook.utils.Constant
import com.skg.mybook.viewModel.ArticleViewModel
import com.skg.mybook.viewModel.sharedViewModel.HomeDetailSharedViewModel
import com.skg.mybook.viewmodel.sharedViewModel.SaveDetailSharedViewModel
import com.skg.mybook.viewmodel.sharedViewModel.SharedViewModel
class DetailFragment: Fragment() {
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var viewModel: ArticleViewModel
    private lateinit var binding: FragmentDetailBinding
    private lateinit var mArticle: Article
    private var fromHome:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false
        )
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments?.getString(Constant.SCREEN_NAME)

        viewModel =
            ViewModelProviders.of(this, BaseViewModelFactory { ArticleViewModel(activity!!) })
                .get(ArticleViewModel::class.java)

        if(args == Constant.HOME){
            sharedViewModel = activity?.run {
                ViewModelProviders.of(this)[HomeDetailSharedViewModel::class.java]
            } ?: throw Exception("Invalid Activity")
            fromHome = true
        } else{
            sharedViewModel =  activity?.run {
                ViewModelProviders.of(this)[SaveDetailSharedViewModel::class.java]
            } ?: throw Exception("Invalid Activity")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel.getArticle().observe(this, Observer { article ->
            binding.article = article
            binding.fromHome = fromHome
            mArticle = article
        })
        binding.saveImage.setOnClickListener {viewModel.saveArticle(mArticle)}
    }
}


