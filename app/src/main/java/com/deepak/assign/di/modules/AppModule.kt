package com.deepak.assign.di.modules

import android.app.Application
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import org.jetbrains.anko.defaultSharedPreferences
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application) = application.defaultSharedPreferences
}

