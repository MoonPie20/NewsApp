package com.comviva.mobiquity.newapp

import com.comviva.mobiquity.newapp.country.Country
import com.comviva.mobiquity.newapp.news.Article

object Router {
    var listofArticles = ArrayList<Article>()
    var listOfCOuntries = ArrayList<Country>()
    var countrySelected : String = ""
    lateinit var countrySelectedCode : String


}