package com.comviva.mobiquity.newapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.comviva.mobiquity.newapp.Router.countrySelected
import com.comviva.mobiquity.newapp.Router.countrySelectedCode
import com.comviva.mobiquity.newapp.Router.listOfCOuntries
import com.comviva.mobiquity.newapp.Router.listofArticles
import com.comviva.mobiquity.newapp.country.Country
import com.comviva.mobiquity.newapp.country.StateListAdapter
import com.comviva.mobiquity.newapp.news.News
import com.comviva.mobiquity.newapp.news.NewsService
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialiseCountryList()
        setupSpinner()

    }

    private fun setupSpinner() {
        val countrySpinner = findViewById<Spinner>(R.id.countrySpinner)
        val operatorNameAdapter =
            StateListAdapter(this, R.layout.spinner_text_item, listOfCOuntries)
        countrySpinner.setAdapter(operatorNameAdapter)
        countrySpinner.setPrompt("Select a State")

        countrySpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                l: Long
            ) {
                countrySelectedCode = listOfCOuntries.get(position).countryKey
                countrySelected = listOfCOuntries.get(position).countryValue
                getNews(countrySelectedCode)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                //left blank intentionally
            }
        })
    }

    private fun initialiseCountryList() {
        listOfCOuntries.add(Country("in", "India"))
        listOfCOuntries.add(Country("us", "USA"))
        listOfCOuntries.add(Country("ar", "Argentina"))
        listOfCOuntries.add(Country("il", "Israel"))
        listOfCOuntries.add(Country("gr", "Greece"))
        listOfCOuntries.add(Country("au", "Australia"))
        listOfCOuntries.add(Country("nl", "Netherlands"))
        listOfCOuntries.add(Country("hk", "Hong Kong"))
        listOfCOuntries.add(Country("nz", "New Zealand"))
        listOfCOuntries.add(Country("kr", "South Korea"))
        listOfCOuntries.add(Country("at", "Austria"))
        listOfCOuntries.add(Country("hu", "Hungary"))
        listOfCOuntries.add(Country("ng", "Nigeria"))
        listOfCOuntries.add(Country("se", "Sweden"))
        listOfCOuntries.add(Country("be", "Belgium"))
        listOfCOuntries.add(Country("in", "India"))
        listOfCOuntries.add(Country("no", "Norway"))
        listOfCOuntries.add(Country("ch", "Switzerland"))
    }

    private fun getNews(country: String) {

        val news: Call<News> = NewsService.news.getHeadLines(country, 1)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                listofArticles.clear()
                val news: News? = response.body()

                if (news != null) {
                    Log.d("newsapp", news.toString())
                    listofArticles = news.articles
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