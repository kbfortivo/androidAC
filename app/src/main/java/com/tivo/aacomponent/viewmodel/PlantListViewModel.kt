package com.tivo.aacomponent.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.tivo.aacomponent.MainActivity
import com.tivo.aacomponent.model.Plant
import com.tivo.aacomponent.repository.PlantRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlantListViewModel(private val repository: PlantRepository) : LifecycleObserver {
    private val _plants = MutableLiveData<List<Plant>>()

    val plantList: MutableLiveData<List<Plant>>
        get() = _plants

    private val disposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun createViewModel() {
        android.util.Log.d("[KB]", "createViewModel")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun cleanViewModel() {
        disposable.dispose()
    }

    fun loadPlants() {
        disposable.addAll(repository.getPlants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { plants, s ->
                android.util.Log.d("[KB]", "on plants loaded; $plants")
                _plants.postValue(plants)
            })
    }

}