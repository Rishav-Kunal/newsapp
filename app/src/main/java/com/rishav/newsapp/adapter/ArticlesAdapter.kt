package com.rishav.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rishav.newsapp.data.Article
import com.rishav.newsapp.databinding.ArticleViewItemBinding

class ArticlesAdapter() :
    RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ArticleViewItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindArticles(article: Article){
            binding.article = article
        }
    }
    var articlesList : List<Article> = ArrayList<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ArticleViewItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = articlesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindArticles(articlesList[position])
}