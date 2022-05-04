package com.comviva.mobiquity.newapp.news

data class News(val totalResults: String, val articles: ArrayList<Article>)
data class SearchResults(val totalResults: String, val articles: ArrayList<Article>)