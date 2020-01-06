package com.arkady.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arkady.news.data.Article
import com.arkady.news.data.News

class NewsViewModel : ViewModel() {
    private val newsRepo: NewsRepo = NewsRepo()
    val articleList : LiveData<News> = newsRepo.getNews()

    private val _selectedArticle : MutableLiveData<Article?> = MutableLiveData()
    val selectedArticle : LiveData<Article?> = _selectedArticle

    fun onArticleClicked(article: Article) {
        _selectedArticle.value = article
    }
}