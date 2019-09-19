package com.tivo.aacomponent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.tivo.aacomponent.adapter.PlantListAdapter
import com.tivo.aacomponent.databinding.ActivityMainBinding
import com.tivo.aacomponent.repository.PlantRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class MainActivity : Activity() {

    //Inject repository
    val repository: PlantRepository by inject()
    val disposable = CompositeDisposable()
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PlantListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // change this to use data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initPlantList()
    }

    private fun initPlantList() {
        adapter = PlantListAdapter(R.layout.plant_list_view_item, listOf(null))
        binding.plantsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.plantsList.adapter = adapter
        adapter.clickListener = { view ->
            val plantId = view.findViewById<View>(R.id.plant_id).tag as Int
            val intent = Intent(this, PlantDetailsActivity::class.java)
            intent.putExtra(PlantDetailsActivity.PLANT_ID_TAG, plantId)
            startActivity(intent)
        }

        disposable.addAll(repository.getPlants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { plants, s ->
                adapter.setPlants(plants)
            })
    }
}
