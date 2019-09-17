package com.tivo.aacomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tivo.aacomponent.adapter.PlantListAdapter
import com.tivo.aacomponent.repository.PlantRepository

class MainActivity : AppCompatActivity() {

    private lateinit var repository: PlantRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // change this to use view model
        initPlantList()
        setContentView(R.layout.activity_main)
    }

    private fun initPlantList() {
        val plants = repository.getPlants()
        val adapter = PlantListAdapter(R.layout.plant_list_view_item, plants)

    }
}
