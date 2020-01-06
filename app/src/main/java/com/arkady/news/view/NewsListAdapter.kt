package com.arkady.news.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arkady.news.data.Article
import com.arkady.news.data.News

class NewsListAdapter(val news: News, var onArticleSelected: ((Article) -> Unit)? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArticleViewHolder(ArticleItemView(parent.context))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ArticleViewHolder) {
            news.articles[position].let { article ->
                holder.articleView.setData(article)
                holder.articleView.setOnClickListener { onArticleSelected?.invoke(article) }
            }
        }
    }

    override fun getItemCount(): Int {
        return news.articles.size
    }
}

class ArticleViewHolder(val articleView: ArticleItemView) : RecyclerView.ViewHolder(articleView)