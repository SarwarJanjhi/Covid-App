package com.anew.retrofitdataretrievepractice

data class CountryInfo(
    val country: String,
    val countryInfo: CountryDetails,
    val cases: Int,
    val todayCases: Int,
    val deaths: Int,
    val todayDeaths: Int,
    val recovered: Int,
    val active: Int,
    val critical: Int,
    val casesPerOneMillion: Double,
    val deathsPerOneMillion: Double,
    val updated: Long
)

data class CountryDetails(
    val _id: Int,
    val flag: String
)
