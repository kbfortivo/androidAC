package com.tivo.aacomponent

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
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
                binding.plant = plant
            })
    }

    companion object {
        const val PLANT_ID_TAG = "plant_id_tag"
    }
}