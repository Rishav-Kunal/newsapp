package com.rishav.newsapp.repository

import com.rishav.newsapp.data.NewsApiResponse
import com.rishav.newsapp.network.NetworkApiService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


class NewsRepository @Inject constructor(private val networkApiService: NetworkApiService) {
    fun getNews():Observable<NewsApiResponse>{
        val observable =  networkApiService.getTopHeadlines()

        return observable
    }
}