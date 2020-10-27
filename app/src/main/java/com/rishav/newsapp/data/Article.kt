package com.rishav.newsapp.data

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.rishav.newsapp.R


data class Article(
    val source: Source,
    val author:String?,
    val title:String,
    val description:String,
    val url:String,
    val urlToImage:String,
    val publishedAt:String,
    val content:String
) {
    companion object {
        @JvmStatic
        @BindingAdapter("thumbnailUrl")
        fun loadImage(view:ImageView, imageUrl:String){
            Glide.with(view.context)
                .load(imageUrl)
                .error(view.context.resources.getDrawable(R.drawable.placeholder_image))
                .into(view)
        }
    }
}