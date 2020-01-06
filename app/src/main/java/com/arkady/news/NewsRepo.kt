package com.arkady.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arkady.news.data.News
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NewsRepo {

    //Just putting this in here for brevity
    private val httpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url()

            val url: HttpUrl = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", "b575986f7e484ac9b10d8086554c8de1")
                .build()

            val request: Request = original.newBuilder()
                .url(url)
                .build()

            chain.proceed(request)
        }.build()

    //Just putting this in here for brevity
    private val retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val newsService: NewsService = retrofit.create(NewsService::class.java)

    fun getNews(): LiveData<News> {
        val data = MutableLiveData<News>()
        newsService.topArticles().enqueue(object : Callback<News?> {
            override fun onResponse(call: Call<News?>, response: Response<News?>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<News?>, t: Throwable) {
                //no-op
            }
        })
        return data
    }
}