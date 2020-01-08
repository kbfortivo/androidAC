package com.tivo.aacomponent.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.tivo.aacomponent.PlantDetailsActivity
import com.tivo.aacomponent.PlantDetailsActivity.Companion.PLANT_ID_TAG
import com.tivo.aacomponent.model.Plant
import com.tivo.aacomponent.repository.PlantRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlantDetailsViewModel(
    private val repository: PlantRepository,
    private val activity: PlantDetailsActivity
) {
    private val _plant = MutableLiveData<Plant>()

    val plant: MutableLiveData<Plant>
        get() = _plant

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
        val plantId = activity.intent.getIntExtra(PLANT_ID_TAG, -1)
        disposable.addAll(repository.getPlant(plantId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                    result, s ->
                _plant.postValue(result)
            })
    }
}