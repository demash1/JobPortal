package com.deepak.assign.di.modules

import com.deepak.assign.ui.MainActivity
import com.deepak.assign.di.scopes.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}