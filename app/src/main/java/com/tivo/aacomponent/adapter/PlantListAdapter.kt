package com.tivo.aacomponent.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tivo.aacomponent.R
import com.tivo.aacomponent.model.Plant

class PlantListAdapter(private val resId: Int, plants: List<Plant?>)
    : RecyclerView.Adapter<PlantListAdapter.ViewHolder>() {
    private val plantsList = plants.toMutableList()
    var clickListener : ((View) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            PLANT_ITEM -> ViewHolder(LayoutInflater.from(parent.context).inflate(resId, parent, false), viewType)
            else -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.plant_list_view_item_progress, parent, false), viewType)
        }
    }

    override fun getItemCount() = plantsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        plantsList[position]?.let { plant ->
            holder.plantName?.text = plant.common_name ?: plant.scientific_name
            holder.plantId?.tag = plant.id
            if (plant.images.isNotEmpty()) {
                holder.plantImage?.let {
                    Glide
                        .with(holder.itemView.context)
                        .load(plant.images[0].url)
                        .placeholder(R.drawable.rose_img)
                        .into(it)
                }
            }
            holder.itemView.setOnClickListener(clickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (plantsList[position] != null) PLANT_ITEM else LOADING_ITEM
    }

    fun setPlants(plants: List<Plant>) {
        plantsList.clear()
        plantsList.addAll(plants)
        notifyDataSetChanged()
    }

    class ViewHolder(root: View, private val viewType: Int) : RecyclerView.ViewHolder(root) {
        val plantName: TextView?
        val plantId: View?
        val plantImage: ImageView?
        init {
            if (viewType == PLANT_ITEM) {
                plantName = root.findViewById(R.id.plant_name)
                plantId = root.findViewById(R.id.plant_id)
                plantImage = root.findViewById(R.id.plant_image)
            } else {
                plantName = null
                plantId = null
                plantImage = null
            }
        }
    }

    companion object {
        const val PLANT_ITEM = 0
        const val LOADING_ITEM = 1
    }
}