package com.tivo.aacomponent

import android.content.Intent
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tivo.aacomponent.adapter.PlantListAdapter
import com.tivo.aacomponent.model.Plant

object RecyclerUtil {
    @JvmStatic
    @BindingAdapter("app:plants")
    fun setPlants(recycler: RecyclerView, plants: List<Plant>?) {
        plants?.let {
            val adapter = PlantListAdapter(R.layout.plant_list_view_item, plants)
            recycler.layoutManager =
                LinearLayoutManager(recycler.context, LinearLayoutManager.VERTICAL, false)
            recycler.adapter = adapter
            adapter.listener = { plant ->
                val intent = Intent(recycler.context, PlantDetailsActivity::class.java)
                intent.putExtra(PlantDetailsActivity.PLANT_ID_TAG, plant.id)
                recycler.context.startActivity(intent)
            }
        }
    }
}