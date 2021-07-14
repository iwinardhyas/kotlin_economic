package com.example.economic.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiEndPoint {

    @GET("data.php")
    fun getData(): Call<MainModel>
}