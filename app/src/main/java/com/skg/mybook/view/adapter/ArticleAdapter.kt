package com.skg.mybook.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skg.mybook.databinding.HomeListItemBinding
import com.skg.mybook.model.Article

class ArticleAdapter(articles: List<Article>, listener: ItemClickListener) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    var articles: List<Article> = articles
    var listener: ItemClickListener = listener

    interface ItemClickListener {
        fun onItemClicked(article: Article, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeListItemBinding.inflate(inflater, parent, false)
        return ArticleViewHolder(binding.itemContainer, binding)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) = holder.bind(articles[position])

    class ArticleViewHolder(itemView: View, binding: HomeListItemBinding) : RecyclerView.ViewHolder(itemView) {
        private var dataBinding: HomeListItemBinding ?= null
        init {
            dataBinding = binding
        }
        fun bind(article: Article) {
            dataBinding?.article = article
            dataBinding?.executePendingBindings()
        }

    }

}
