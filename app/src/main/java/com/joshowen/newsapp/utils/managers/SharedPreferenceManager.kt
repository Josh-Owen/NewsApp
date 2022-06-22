package com.joshowen.newsapp.utils.managers

import android.content.Context
import android.content.SharedPreferences
import com.joshowen.newsapp.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class SharedPreferenceManager @Inject constructor(@ApplicationContext private val context: Context, private val preferences: SharedPreferences) {

    fun isDarkModeEnabled() = preferences.getBoolean(context.getString(R.string.pref_dark_mode_key), false)

}