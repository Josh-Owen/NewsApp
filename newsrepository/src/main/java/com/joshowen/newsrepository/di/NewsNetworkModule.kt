package com.joshowen.newsrepository.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.joshowen.newsrepository.API_KEY
import com.joshowen.newsrepository.BuildConfig
import com.joshowen.newsrepository.retrofit.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsNetworkModule {

    //region variables
    private const val BASE_NEWS_API_URL = "https://newsapi.org/v2/"

    //endregion

    @Provides
    @Singleton
    fun getGson(): Gson {
        return GsonBuilder().serializeNulls().setLenient().create()
    }

    @Provides
    @Singleton
    @Named("apiKeyInterceptor")
    fun getAPIKeyInterceptor(): Interceptor {
        return Interceptor {
            it.proceed(
                it.request()
                    .newBuilder()
                    .addHeader("X-Api-Key", API_KEY)
                    .build()
            )
        }
    }

    @Provides
    @Singleton
    @Named("httpLoggingInterceptor")
    fun getHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
    }

    @Provides
    @Singleton
    fun getOkHttpClient(
        @Named("httpLoggingInterceptor") httpLoggingInterceptor: Interceptor,
        @Named("apiKeyInterceptor") apiKeyInterceptor: Interceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)

        return httpBuilder
            .protocols(mutableListOf(Protocol.HTTP_1_1))
            .build()
    }

    @Provides
    @Singleton
    fun getRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_NEWS_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun getNewsService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }
}

