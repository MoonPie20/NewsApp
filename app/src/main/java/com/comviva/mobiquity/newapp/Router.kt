package com.comviva.mobiquity.newapp

import com.comviva.mobiquity.newapp.country.Country
import com.comviva.mobiquity.newapp.news.Article

object Router {
    var listofArticles = ArrayList<Article>()
    var listOfCOuntries = ArrayList<Country>()
    var countrySelected : String = ""

    var selectedArticleTitle : String?=null
    var selectedArticleDescription : String?=null
    var selectedArticleAuthor : String?=null
    var selectedArticledate : String?=null
    var selectedArticleurl : String?=null
    var selectedArticleurlImage : String?=null
     var countrySelectedCode : String?=null


}