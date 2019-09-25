package com.tivo.aacomponent

import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tivo.aacomponent.adapter.PlantListAdapter
import com.tivo.aacomponent.model.Plant

object BindingUtil {
    @JvmStatic
    @BindingAdapter("plants")
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

    @JvmStatic
    @BindingAdapter( value = ["template", "android:text"], requireAll = true)
    fun setTextWithTemplate(textView: TextView, templateId: Int, text: String?) {
        textView.text = textView.context.getString(templateId, text)
    }

    @JvmStatic
    @BindingAdapter(value = ["placeholder", "url"], requireAll = true)
    fun setImageFromUrl(imageView: ImageView, placeholderId: Int, url: String?) {
        Glide
            .with(imageView.context)
            .load(url)
            .placeholder(placeholderId)
            .into(imageView)
    }
}