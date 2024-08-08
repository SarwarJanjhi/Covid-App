package com.anew.retrofitdataretrievepractice

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("countries")

    fun getCountrYtData(): Call<List<CountryInfo>>
}