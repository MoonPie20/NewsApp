package com.comviva.mobiquity.newapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.comviva.mobiquity.newapp.Router.listofArticles
import com.comviva.mobiquity.newapp.news.News
import com.comviva.mobiquity.newapp.news.NewsService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNews()
    }

    private fun getNews() {
        val news: Call<News> = NewsService.news.getHeadLines("in", 1)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                //  Router.listofArticles.clear()
                val news: News? = response.body()
                if (news != null) {
                    Log.d("newsapp", news.toString())
                    Router.listofArticles = news.articles
                    adapter = NewsAdapter(this@MainActivity, listofArticles)
                    newsListDisplay.adapter = adapter
                    newsListDisplay.layoutManager = LinearLayoutManager(this@MainActivity)
                }

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("newsapp", "Error in api")
            }

        })

    }
}