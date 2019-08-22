package com.skg.mybook.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.skg.mybook.R
import com.skg.mybook.common.BaseViewModelFactory
import com.skg.mybook.databinding.FragmentDetailBinding
import com.skg.mybook.model.Article
import com.skg.mybook.viewModel.ArticleViewModel
import com.skg.mybook.viewModel.sharedViewModel.HomeDetailSharedViewModel
import com.skg.mybook.viewModel.sharedViewModel.SharedViewModel

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailFragment() : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var viewModel: ArticleViewModel
    private lateinit var binding: FragmentDetailBinding
    private lateinit var mArticle: Article
    var screen: String = ""
    val args: HomeFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false
        );
        return binding.root
        screen = args.screen

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (screen.equals("home")) {
            sharedViewModel = activity?.run {
                ViewModelProviders.of(this)[HomeDetailSharedViewModel::class.java]
            } ?: throw Exception("Invalid Activity")
        }

        viewModel =
            ViewModelProviders.of(this, BaseViewModelFactory { ArticleViewModel(activity!!) })
                .get(ArticleViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel.getArticle().observe(this, Observer { article ->
            binding.article = article
            mArticle = article
        })
        binding.saveImage.setOnClickListener { viewModel.saveArticle(article = mArticle) }
    }
}

