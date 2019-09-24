package com.tivo.aacomponent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.tivo.aacomponent.adapter.PlantListAdapter
import com.tivo.aacomponent.databinding.ActivityMainBinding
import com.tivo.aacomponent.repository.PlantRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class MainActivity : Activity() {

    //Inject repository
    val repository: PlantRepository by inject()
    val disposable = CompositeDisposable()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // change this to use data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initPlantList()
    }

    private fun initPlantList() {

        disposable.addAll(repository.getPlants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { plants, s ->
                binding.plants = plants
            })
    }
}
