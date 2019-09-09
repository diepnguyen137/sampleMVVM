package com.skg.mybook.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skg.mybook.databinding.HomeListItemBinding
import com.skg.mybook.model.Article

class ArticleAdapter(listener: ItemClickListener) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    var articles = ArrayList<Article>()
    var listener: ItemClickListener = listener

    interface ItemClickListener {
        fun onItemClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeListItemBinding.inflate(inflater, parent, false)
        return ArticleViewHolder(binding.itemContainer, binding)
    }
    override fun getItemCount(): Int {
        return articles.size
    }
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) = holder.bind(articles[position], listener, position)

    fun setAdapter(articles: ArrayList<Article> ){
        this.articles = articles
        notifyDataSetChanged()
    }
    fun resetAdapter(){
        this.articles.clear()
        notifyDataSetChanged()
    }
    class ArticleViewHolder(itemView: View, binding: HomeListItemBinding) : RecyclerView.ViewHolder(itemView) {
        private var dataBinding: HomeListItemBinding ?= null
        init {
            dataBinding = binding
        }
        fun bind(
            article: Article,
            listener: ItemClickListener,
            position: Int
        ) {
            dataBinding?.article = article
            dataBinding?.itemContainer?.setOnClickListener { listener.onItemClicked(position) }
            dataBinding?.executePendingBindings()
        }

    }

}
