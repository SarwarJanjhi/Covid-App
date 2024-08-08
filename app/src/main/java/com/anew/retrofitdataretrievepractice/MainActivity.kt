package com.anew.retrofitdataretrievepractice

import RetrofitWorkService
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Utils.checkInternetConnectivity(this)

        iv_search_image.setOnClickListener {
            val intent = Intent(this, SearchAlgoritham::class.java)
            startActivity(intent)
        }

        val recyclerView: RecyclerView = findViewById(R.id.CountryRecyclerView)
        progressBar = findViewById(R.id.progressBar)
        imageView = findViewById(R.id.iv_search_image)

        val retrofitWorkService = object : RetrofitWorkService(recyclerView) {}
        retrofitWorkService.getdata(progressBar, imageView)
    }
}
