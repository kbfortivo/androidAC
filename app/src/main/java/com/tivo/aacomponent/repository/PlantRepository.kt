package com.tivo.aacomponent.repository

import com.tivo.aacomponent.model.Plant
import com.tivo.aacomponent.model.PlantListJson
import io.reactivex.Flowable
import io.reactivex.Single

interface PlantRepository {
    fun getPlants(): Single<List<Plant>>
    fun getPlant(id: Int): Single<Plant>
}