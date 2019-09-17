package com.tivo.aacomponent.repository

import com.tivo.aacomponent.model.Plant

interface PlantRepository {
    fun getPlants(): List<Plant>
}