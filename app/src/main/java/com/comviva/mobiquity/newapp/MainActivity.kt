package com.comviva.mobiquity.newapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.comviva.mobiquity.newapp.Router.countrySelected
import com.comviva.mobiquity.newapp.Router.countrySelectedCode
import com.comviva.mobiquity.newapp.Router.listOfCOuntries
import com.comviva.mobiquity.newapp.Router.listofArticles
import com.comviva.mobiquity.newapp.Router.listofSearchResults
import com.comviva.mobiquity.newapp.news.News
import com.comviva.mobiquity.newapp.news.NewsAdapter
import com.comviva.mobiquity.newapp.news.NewsService
import com.comviva.mobiquity.newapp.news.SearchResults
import com.comviva.mobiquity.newapp.news.country.Country
import com.comviva.mobiquity.newapp.news.country.StateListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    var pageNumber = 1
    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        listOfCOuntries.clear()
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialiseCountryList()
        setupSpinner()
        setupSearchView()

    }

    private fun setupSearchView() {
        searchView1.setOnClickListener {
            searchView1.isIconified = false
            searchView1.setIconifiedByDefault()
        }
        searchView1.queryHint = "Search..."
        searchView1.setOnCloseListener {
            noNewsFound.visibility = View.GONE
            newsListDisplay.visibility = View.VISIBLE

            false
        }
        searchView1.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                getSearchResults(searchView1.query.toString())
                return false
            }

        })
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
                pageNumber = 1
                countrySelectedCode = listOfCOuntries.get(position).countryKey
                countrySelected = listOfCOuntries.get(position).countryValue
                getNews(countrySelectedCode!!, pageNumber)
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

    private fun getSearchResults(searchQuery: String) {

        val news: Call<SearchResults> = NewsService.news.getSearch(searchQuery)
        news.enqueue(object : Callback<SearchResults> {
            override fun onResponse(call: Call<SearchResults>, response: Response<SearchResults>) {
                listofSearchResults.clear()
                val searchResults: SearchResults? = response.body()
                if (searchResults != null) {
                    if (searchResults.totalResults.toInt() == 0) {
                        noNewsFound.visibility = View.VISIBLE
                        newsListDisplay.visibility = View.GONE

                    } else {
                        Log.d("newsapp", news.toString())
                        listofSearchResults = searchResults.articles
                        adapter = NewsAdapter(this@MainActivity, listofSearchResults)
                        newsListDisplay.adapter = adapter
                        newsListDisplay.layoutManager = LinearLayoutManager(this@MainActivity)
                        newsListDisplay.addItemDecoration(
                            DividerItemDecoration(
                                this@MainActivity,
                                LinearLayout.VERTICAL
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<SearchResults>, t: Throwable) {
                Log.d("newsapp", "Error in api")
                noNewsFound.visibility = View.VISIBLE
            }

        })
    }

    private fun getNews(country: String, page: Int) {

        val news: Call<News> = NewsService.news.getHeadLines(country, page)
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
                    newsListDisplay.addItemDecoration(
                        DividerItemDecoration(
                            this@MainActivity,
                            LinearLayout.VERTICAL
                        )
                    )

                }

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("newsapp", "Error in api")
            }

        })

    }
}