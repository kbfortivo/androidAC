package com.tivo.aacomponent

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.tivo.aacomponent.databinding.ActivityMainBinding
import com.tivo.aacomponent.databinding.PlantDetailsViewBinding
import com.tivo.aacomponent.repository.PlantRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class PlantDetailsActivity : Activity() {

    //Inject repository
    val repository: PlantRepository by inject()
    val disposable = CompositeDisposable()
    lateinit var binding: PlantDetailsViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // change this to use data binding
        binding = DataBindingUtil.setContentView(this, R.layout.plant_details_view)
        initPlant()
    }

    override fun onPause() {
        super.onPause()
        disposable.dispose()
    }

    private fun initPlant() {
        disposable.addAll(repository.getPlant(intent.getIntExtra(PLANT_ID_TAG, -1))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                plant, s ->
                plant?.let {
                    binding.plant = it

                    val plantScientific = findViewById<TextView>(R.id.plant_scientific_name)
                    plantScientific.text = getString(R.string.plant_scientific_template_text, it.scientific_name)
                    val plantType = findViewById<TextView>(R.id.plant_type)
                    plantType.text = getString(R.string.plant_type_template_text, it.genus?.name ?: "")
                    val plantFamily = findViewById<TextView>(R.id.plant_family)
                    plantFamily.text = getString(R.string.plant_family_template_text, it.family_common_name ?: "")
                    val plantSpecies = findViewById<TextView>(R.id.plant_species)
                    plantSpecies.text = getString(R.string.plant_species_template_text, it.species.name ?: it.species.scientific_name)
                    if (plant.images.isNotEmpty()) {
                        Glide
                            .with(applicationContext)
                            .load(plant.images[0].url)
                            .placeholder(R.drawable.rose_img)
                            .into(binding.plantImage)
                    }
                }


            })
    }

    companion object {
        const val PLANT_ID_TAG = "plant_id_tag"
    }
}