package com.rishav.newsapp.viewmodel

import android.util.Log
import android.view.View
import androidx.databinding.Bindable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rishav.newsapp.data.Article
import com.rishav.newsapp.data.NewsApiResponse
import com.rishav.newsapp.repository.NewsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

private const val TAG = "NewsViewModel"

class NewsViewModel @ViewModelInject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    val newsObservable:Observable<NewsApiResponse> by lazy {
        newsRepository.getNews()
    }
    var adVisibility:MutableLiveData<Int> = MutableLiveData(0)
}