package com.tivo.aacomponent.server

import com.tivo.aacomponent.model.Plant
import com.tivo.aacomponent.model.PlantListJson
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlantService {
    @GET("api/plants")
    fun getPlants(@Query("token") token: String): Single<List<PlantListJson>>

    @GET("api/plants/{id}")
    fun getPlantById(@Path("id") id: Int, @Query("token") token: String) : Single<Plant>
}