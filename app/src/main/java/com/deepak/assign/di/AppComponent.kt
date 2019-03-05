package com.deepak.assign.di

import android.app.Application
import com.deepak.assign.Assign
import com.deepak.assign.di.modules.*
import com.deepak.assign.di.scopes.ApplicationBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [(AndroidSupportInjectionModule::class), (AppModule::class),
        (ActivityBindingModule::class), (ApiModule::class), (ApplicationBindingModule::class),
        (RxModule::class), (FragmentBindingModule::class)]
)
interface AppComponent : AndroidInjector<Assign> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}

@Singleton
@Component(modules = [AppModule::class, ApiModule::class, ApplicationBindingModule::class, RxModule::class])
interface NonAndroidComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): NonAndroidComponent.Builder

        fun build(): NonAndroidComponent
    }
}
