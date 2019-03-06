package com.deepak.assign.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.deepak.assign.entity.PositionModel
import com.deepak.assign.entity.SearchModel
import com.deepak.assign.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val app: Application,
    private val compositeDisposable: CompositeDisposable,
    private val repository: MainRepository,
    private val schedulerProvider: SchedulerProvider
) : AndroidViewModel(app) {

    var jobResponse = MutableLiveData<List<PositionModel>>()
    var jobResponseSecond = MutableLiveData<List<SearchModel>>()

    fun getPosition(description: String, location: String) {
        Timber.e("@@@ test")
        compositeDisposable.add(
            repository.getPosition(description, location)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(onNext = {
                    jobResponse.value = it

                    it.forEach {
                        Timber.e("@@@@ ${it.company}")

                    }

                }, onError = {

                })
        )
    }

    fun getPositionSecond(query: String) {
        Timber.e("@@@ test")
        compositeDisposable.add(
            repository.getPositionSecond(query)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(onNext = {
                    it.forEach {
                    }
                    jobResponseSecond.value = it

                    it.forEach {
                        Timber.e("@@@@@ ${it}")

                    }

                }, onError = {

                })
        )
    }

    fun getJobList(): MutableLiveData<List<PositionModel>> = jobResponse
}