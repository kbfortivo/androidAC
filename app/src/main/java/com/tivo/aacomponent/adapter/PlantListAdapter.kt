package com.tivo.aacomponent.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tivo.aacomponent.R
import com.tivo.aacomponent.databinding.PlantListViewItemBinding
import com.tivo.aacomponent.model.Plant

class PlantListAdapter(private val resId: Int, plants: List<Plant>)
    : RecyclerView.Adapter<PlantListAdapter.ViewHolder>() {
    private val plantsList = plants.toMutableList()
    var listener : ((Plant) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(DataBindingUtil.inflate(inflater, resId, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = plantsList[position]
        holder.binding.plant = plantsList[position]
        loadImage(plant, holder.binding.plantImage)
        holder.itemView.setOnClickListener {
            listener?.invoke(plant)
        }
    }

    override fun getItemCount() = plantsList.size

    fun setPlants(plants: List<Plant>) {
        plantsList.clear()
        plantsList.addAll(plants)
        notifyDataSetChanged()
    }

    private fun loadImage(plant: Plant, plantImage: ImageView) {
        if (plant.images.isNotEmpty()) {
            Glide
                .with(plantImage.context)
                .load(plant.images[0].url)
                .placeholder(R.drawable.rose_img)
                .into(plantImage)
        }
    }

    class ViewHolder(val binding: PlantListViewItemBinding) : RecyclerView.ViewHolder(binding.root)
}