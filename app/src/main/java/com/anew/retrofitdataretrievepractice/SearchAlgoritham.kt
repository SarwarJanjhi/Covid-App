package com.anew.retrofitdataretrievepractice

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search_algoritham.*

class SearchAlgoritham : AppCompatActivity() {

    private lateinit var userAdapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_algoritham)

        // Access the country list from DataHolder
        val mycountryList = DataHolder.countryList



        // Create the custom adapter and set it to the ListView
        userAdapter = CountryAdapter(this, mycountryList)
        userList.adapter = userAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                userAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                userAdapter.filter.filter(newText)
                return false
            }
        })
    }
}
