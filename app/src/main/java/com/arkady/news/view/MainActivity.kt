package com.arkady.news.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.arkady.news.NewsViewModel
import com.arkady.news.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val fragmentContainerId = R.id.article_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        news_list.layoutManager = LinearLayoutManager(this)

        val viewModel: NewsViewModel = ViewModelProviders.of(this).get(
            NewsViewModel::class.java)

        viewModel.articleList.observe(this, Observer {
            news_list.adapter =
                NewsListAdapter(it) { selectedArticle ->
                    viewModel.onArticleClicked(selectedArticle)
                }
        })

        viewModel.selectedArticle.observe(this, Observer {
            ArticleFragment.show(supportFragmentManager, fragmentContainerId)
        })
    }
}

