package com.arkady.news

import com.arkady.news.data.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsService {
    @GET("v2/top-headlines")
    fun topArticles(@Query("country") country: String = "us"): Call<News?>
}