package com.tivo.aacomponent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.tivo.aacomponent.databinding.ActivityMainBinding
import com.tivo.aacomponent.model.Plant
import com.tivo.aacomponent.viewmodel.PlantListViewModel
import org.koin.androidx.scope.currentScope

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val viewModel: PlantListViewModel by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.plantList.observe(this, Observer<List<Plant>> { plants ->
            binding.plants = plants
        })
        viewModel.loadPlants()
    }
}
