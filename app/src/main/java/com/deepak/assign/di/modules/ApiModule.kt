package com.deepak.assign.di.modules

import android.content.Context
import com.deepak.assign.networking.ProviderFirstApiService
import com.deepak.assign.networking.ProviderSecondApiService
import com.deepak.assign.util.BASE_END_POINT_PROVIDER_1
import com.deepak.assign.util.BASE_END_POINT_PROVIDER_2
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
class ApiModule {
    @Provides
    fun provideOkHttpClient(context: Context): OkHttpClient {
        val cacheSize = 100 * 1024 * 1024 // 10 MB
        val cache = Cache(context.cacheDir, cacheSize.toLong())
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val client = OkHttpClient.Builder()

        client.cache(cache)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)

        client.addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(httpLoggingInterceptor)
        return client.build()
    }

    @Provides
    fun provideApiService(okHttpClient: OkHttpClient): ProviderFirstApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_END_POINT_PROVIDER_1)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(ProviderFirstApiService::class.java)
    }

    @Provides
    fun provideApiServiceSecond(okHttpClient: OkHttpClient): ProviderSecondApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_END_POINT_PROVIDER_2)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(ProviderSecondApiService::class.java)
    }
}