package com.rishav.newsapp.data

data class NewsApiResponse(
    val status : String,
    val totalResults:Int,
    val articles:List<Article>) {
}