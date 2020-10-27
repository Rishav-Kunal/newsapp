package com.rishav.newsapp.util

import android.app.Activity
import android.content.Context
import com.rishav.newsapp.R

class AppUtil() {
    companion object{
        fun setVisibility(visibility : Int, activity:Activity){
            val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
            with (sharedPref.edit()) {
                putInt(activity.getString(R.string.ad_visibility_pref), visibility)
                apply()
            }
        }
        fun getVisibility(activity: Activity) : Int{
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return 0
            return sharedPref.getInt(activity.getString(R.string.ad_visibility_pref), 0)
        }
    }
}