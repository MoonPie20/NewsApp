package com.comviva.mobiquity.newapp.news

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "85c59c8c34fa433e896d0b93be750f4c"
const val END_POINT = "v2/top-headlines?apiKey=$API_KEY"
const val EVERYTHING_END_POINT = "v2/everything?apiKey=$API_KEY"

interface NewsInterface {

    @GET(END_POINT)
    fun getHeadLines(@Query("country") country: String, @Query("page") page: Int): Call<News>

    @GET(EVERYTHING_END_POINT)
    fun getSearch(@Query("q") country: String): Call<SearchResults>

}

//singleton obejct of retrofit
object NewsService {
    val news: NewsInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        news = retrofit.create(NewsInterface::class.java)
    }
}