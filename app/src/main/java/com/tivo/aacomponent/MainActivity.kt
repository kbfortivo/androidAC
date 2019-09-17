package com.tivo.aacomponent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tivo.aacomponent.adapter.PlantListAdapter
import com.tivo.aacomponent.repository.PlantRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class MainActivity : Activity() {

    //Inject repository
    val repository: PlantRepository by inject()
    val disposable = CompositeDisposable()
    private val adapter: PlantListAdapter = PlantListAdapter(R.layout.plant_list_view_item, listOf(null))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // change this to use view model
        setContentView(R.layout.activity_main)
        initPlantList()
    }

    private fun initPlantList() {
        disposable.addAll(repository.getPlants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { plants, s ->
                adapter.setPlants(plants)
            })
        adapter.clickListener = { view ->
            val plantId = view.findViewById<View>(R.id.plant_id).tag as Int
            val intent = Intent(this, PlantDetailsActivity::class.java)
            intent.putExtra(PlantDetailsActivity.PLANT_ID_TAG, plantId)
            startActivity(intent)
        }
        val recycler = findViewById<RecyclerView>(R.id.plants_list)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adapter
    }
}
