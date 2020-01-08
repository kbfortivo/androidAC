package com.tivo.aacomponent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.tivo.aacomponent.databinding.PlantDetailsViewBinding
import com.tivo.aacomponent.model.Plant
import com.tivo.aacomponent.viewmodel.PlantDetailsViewModel
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.currentScope

class PlantDetailsActivity : AppCompatActivity() {

    //Inject repository
    val viewModel: PlantDetailsViewModel by currentScope.inject()
    lateinit var binding: PlantDetailsViewBinding

    init {
        getKoin().setProperty("activityContext", this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // change this to use data binding
        binding = DataBindingUtil.setContentView(this, R.layout.plant_details_view)
        initPlant()
    }

    private fun initPlant() {
        viewModel.plant.observe(this, Observer<Plant> { plant ->
            binding.plant = plant
        })
        viewModel.loadPlants()
    }

    companion object {
        const val PLANT_ID_TAG = "plant_id_tag"
    }
}