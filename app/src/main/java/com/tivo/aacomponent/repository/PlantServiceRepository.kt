package com.tivo.aacomponent.repository

import com.tivo.aacomponent.model.Plant
import com.tivo.aacomponent.model.PlantListJson
import com.tivo.aacomponent.server.PlantService
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class PlantServiceRepository : PlantRepository {
    private val plantService = Retrofit
            .Builder()
            .baseUrl("https://trefle.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(PlantService::class.java)


    override fun getPlants(): Single<List<Plant>> {
        return Single.create {
            val plants = plantService.getPlants(TOKEN)
                .blockingGet()
            it.onSuccess(plants.map { plantService.getPlantById(it.id, TOKEN).blockingGet() })
        }
    }

    override fun getPlant(id: Int): Single<Plant> {
        return plantService.getPlantById(id, TOKEN)
    }

    companion object {
        const val TOKEN = "MnlLWnByamp0MmF3TkRZeFhONnJxQT09"
    }
}