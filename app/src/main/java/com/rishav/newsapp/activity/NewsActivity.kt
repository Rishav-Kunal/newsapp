package com.rishav.newsapp.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import com.rishav.newsapp.R
import com.rishav.newsapp.adapter.ArticlesAdapter
import com.rishav.newsapp.data.NewsApiResponse
import com.rishav.newsapp.databinding.ActivityNewsBinding
import com.rishav.newsapp.util.AppUtil
import com.rishav.newsapp.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

private const val TAG = "NewsActivity"

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {
    private val viewModel : NewsViewModel by viewModels()
    private val articlesAdapter = ArticlesAdapter()
    private lateinit var binding: ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_news)
        binding.viewmodel = viewModel
        binding.rvArticles.adapter = articlesAdapter
        initialise()
        subscribeNews()
    }
    private fun initialise(){
        viewModel.adVisibility.value = AppUtil.getVisibility(this)
        if(AppUtil.getVisibility(this) == View.VISIBLE){
            showAdBanner()
        }
        initClickListeners()
    }
    private fun initClickListeners(){
        binding.ivClose.setOnClickListener {
            AppUtil.setVisibility(View.GONE,this)
            hideAdBanner()
        }
    }
    private fun subscribeNews(){
        viewModel.newsObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {newsResponse -> setData(newsResponse)},
                {error -> Log.d(TAG, error.message)}
            )
    }
    private fun setData(newsResponse: NewsApiResponse?){
        newsResponse?.let {
            articlesAdapter.articlesList = it.articles
            articlesAdapter.notifyDataSetChanged()
        }
    }

    private fun showAdBanner(){
        val constraint = ConstraintSet()
        constraint.clone(binding.clParent)
        constraint.connect(
            binding.groupAd.id,
            ConstraintSet.TOP,
            binding.horizontalGuide.id,
            ConstraintSet.BOTTOM,
            0
        )
        constraint.applyTo(binding.clParent)
        binding.groupAd.visibility = View.VISIBLE
    }
    private fun hideAdBanner(){
        binding.groupAd.visibility = View.GONE
        val constraint = ConstraintSet()
        constraint.clone(binding.clParent)
        constraint.connect(
            binding.groupAd.id,
            ConstraintSet.TOP,
            binding.clParent.id,
            ConstraintSet.BOTTOM,
            0
        )
        constraint.applyTo(binding.clParent)
    }
}