package com.deepak.assign.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.deepak.assign.entity.PositionModel
import com.deepak.assign.entity.SearchModel
import com.deepak.assign.util.SchedulerProvider
import com.deepak.assign.util.toPositionModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    val app: Application,
    private val schedulerProvider: SchedulerProvider,
    private val repository: MainRepository
) : AndroidViewModel(app) {

    var jobResponse = MutableLiveData<List<PositionModel>>()
    var jobResponseSecond = MutableLiveData<List<SearchModel>>()

    fun getPosition() {
        Timber.e("@@@ test")
        compositeDisposable.add(
            repository.getPosition()
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