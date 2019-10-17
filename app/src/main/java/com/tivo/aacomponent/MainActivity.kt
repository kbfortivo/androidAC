package com.tivo.aacomponent

import android.app.Activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.tivo.aacomponent.databinding.ActivityMainBinding
import com.tivo.aacomponent.model.Plant
import com.tivo.aacomponent.repository.PlantRepository
import com.tivo.aacomponent.viewmodel.PlantListViewModel
import org.koin.android.ext.android.inject

class MainActivity : Activity(), LifecycleOwner, PlantListViewModel.ViewModelListener {

    lateinit var binding: ActivityMainBinding
    lateinit var lifecycleRegistry: LifecycleRegistry
    //Inject repository
    val repository: PlantRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleRegistry = LifecycleRegistry(this)
        lifecycle.addObserver(PlantListViewModel(repository, this))
        // change this to use data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    override fun onComplete(plants: List<Plant>) {
        binding.plants = plants
    }
}
