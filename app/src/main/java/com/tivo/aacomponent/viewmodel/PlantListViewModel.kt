package com.tivo.aacomponent.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.tivo.aacomponent.model.Plant
import com.tivo.aacomponent.repository.PlantRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlantListViewModel(
    private val repository: PlantRepository,
    private val listener: ViewModelListener
) : LifecycleObserver {
    interface ViewModelListener {
        fun onComplete(plants: List<Plant>)
    }

    val disposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun createViewModel() {
        disposable.addAll(repository.getPlants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { plants, s ->
                listener.onComplete(plants)
            })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun cleanViewModel() {
        disposable.dispose()
    }

}