package com.example.economic.retrofit

data class MainModel ( val result : ArrayList<Result>){
    data class Result (val id: Int, val title: String, val image: String)
}