package com.skg.mybook.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skg.mybook.R
import com.skg.mybook.model.Article

class ArticleAdapter(context:Context , articles:List<Article>, listener: ItemClickListener) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    var context:Context = context
    var articles:List<Article> = articles
    var listener: ItemClickListener = listener

    interface ItemClickListener {
        fun onItemClicked(article: Article, position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ArticleViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.mTitleView?.text = article.title
        holder.mAuthorView?.text = article.author
        holder.mItemContainer?.setOnClickListener { listener.onItemClicked(article,position) }
    }

    class ArticleViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.home_list_item, parent, false)) {
        var mTitleView: TextView ?= null
        var mAuthorView:TextView ?= null
        var mImageView:ImageView ?= null
        var mItemContainer: ConstraintLayout ?= null

        init {
            mTitleView = itemView.findViewById(R.id.title_tv)
            mAuthorView = itemView.findViewById(R.id.author_tv)
            mImageView = itemView.findViewById(R.id.home_image)
            mItemContainer = itemView.findViewById(R.id.item_container)
        }
    }
    @BindingAdapter("articleImage")
    fun loadImage(view: ImageView, imgUrl: String){
        Glide.with(context).load(imgUrl).centerCrop().into(view)
    }
}
