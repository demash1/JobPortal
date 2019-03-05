package com.deepak.assign.di.modules

import com.deepak.assign.util.BaseSchedulerProvider
import com.deepak.assign.util.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class RxModule {

    @Provides
    fun providesCompositeDisposable() = CompositeDisposable()

    @Provides
    fun providesSchedulerProvider() = SchedulerProvider()

    @Provides
    fun providesBaseSchedulerProvider(): BaseSchedulerProvider = SchedulerProvider()
}