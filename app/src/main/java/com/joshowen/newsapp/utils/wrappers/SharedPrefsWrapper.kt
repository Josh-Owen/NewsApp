package com.joshowen.newsapp.utils.wrappers

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.joshowen.newsapp.R

class SharedPrefsWrapper {

    companion object {
        private fun getPreferenceManager(context: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(context)
        }

        fun getIsDarkModeEnabled(context: Context, defaultValue : Boolean = false) : Boolean {
            return getPreferenceManager(context)
                .getBoolean(
                    context.resources.getString(R.string.pref_dark_mode_key),
                    defaultValue
                )
        }


    }

}