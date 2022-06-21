package com.joshowen.newsapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.joshowen.newsapp.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object SharedPreferenceModule {

    @Provides
    @Singleton
    @Named("sharedPreferences")
    fun getSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    @Named("darkModeEnabled")
    fun provideIsDarkModeEnabledState(@ApplicationContext context: Context, @Named("sharedPreferences") sharedPrefs : SharedPreferences) : Boolean {
        return sharedPrefs.getBoolean(context.resources.getString(R.string.pref_dark_mode_key), false)
    }
}