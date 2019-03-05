package com.deepak.assign

import androidx.fragment.app.Fragment
import com.deepak.assign.di.DaggerAppComponent
import com.deepak.assign.di.DaggerNonAndroidComponent
import com.deepak.assign.di.NonAndroidComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

class Assign : DaggerApplication(), HasActivityInjector, HasSupportFragmentInjector {

    var nonAndroidComponent: NonAndroidComponent? = null

    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        nonAndroidComponent = DaggerNonAndroidComponent.builder().application(this).build()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent
        .builder()
        .application(this)
        .build()

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mFragmentInjector
    }
}