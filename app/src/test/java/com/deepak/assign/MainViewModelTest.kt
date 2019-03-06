package com.deepak.assign

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.deepak.assign.ui.MainRepository
import com.deepak.assign.ui.MainViewModel
import com.deepak.assign.util.SchedulerProvider
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MainViewModelTest {

    private val applicationMock = Mockito.mock(Application::class.java)
    private val compositeDisposable = CompositeDisposable()
    private val repositoryMock = Mockito.mock(MainRepository::class.java)

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getJobResponse() {
    }

    @Test
    fun setJobResponse() {
    }

    @Test
    fun getJobResponseSecond() {
    }

    @Test
    fun setJobResponseSecond() {
    }

    @Test
    fun getPosition() {
        val viewModel = MainViewModel(
            applicationMock,
            compositeDisposable,
            repositoryMock,
            SchedulerProvider()
        )
       // viewModel.getPositionSecond("test")
        //  Mockito.verify(viewModel.getJobList())
    }

    @Test
    fun getPositionSecond() {
    }

    @Test
    fun getJobList() {
    }

    @Test
    fun getApp() {
    }
}