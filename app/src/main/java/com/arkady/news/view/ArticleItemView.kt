package com.arkady.news.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.arkady.news.R
import com.arkady.news.data.Article
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_article_item.view.*

class ArticleItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    init {
        inflate(context, R.layout.view_article_item, this)
    }

    fun setData(article: Article) {
        Glide.with(this)
            .load(article.urlToImage)
            .into(image)

        title.text = article.title
    }
}