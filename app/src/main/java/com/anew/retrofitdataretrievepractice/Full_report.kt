package com.anew.retrofitdataretrievepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_full_report.*

class Full_report : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_report)

        // Retrieve data passed from the intent
        val countryName = intent.getStringExtra("COUNTRY_NAME")
        val covidCases = intent.getStringExtra("COVID_CASES")
        val flagUrl = intent.getStringExtra("FLAG_URL")
        val deathNum = intent.getStringExtra("DEATHS_NUM")
        val activeNum = intent.getStringExtra("ACTIVE_NUM")
        val recoveredNum = intent.getStringExtra("RECOVERED_NUM")

        // Set the retrieved data to views
        tv_countryname.text = countryName
        tv_cases.text = "+$covidCases"
        Picasso.get().load(flagUrl).into(iv_flag)
        tv_active.text = "+$activeNum"
        tv_deaths.text = "+$deathNum"
        tv_recovred.text = "+$recoveredNum"
    }
}
