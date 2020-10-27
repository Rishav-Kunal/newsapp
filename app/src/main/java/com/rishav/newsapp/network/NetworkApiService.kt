package com.rishav.newsapp.network

import com.rishav.newsapp.data.NewsApiResponse
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

const val api_key = "a958d6351b004927b04b410aa9744e83"
interface NetworkApiService {
    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country:String="in", @Query("apiKey") apiKey:String = api_key ): Observable<NewsApiResponse>
}