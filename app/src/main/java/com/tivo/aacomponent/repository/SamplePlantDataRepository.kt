package com.tivo.aacomponent.repository

import com.tivo.aacomponent.model.Genus
import com.tivo.aacomponent.model.Plant
import com.tivo.aacomponent.model.Species
import io.reactivex.Single

class SamplePlantDataRepository : PlantRepository {
    private val defGenus = Genus(1,"Rosa", "", "")
    private val defSpecies = Species(1,"Rosa", "Rosa", true)
    private val allPlants = listOf(
        Plant(1, "Rose", "Rose",defGenus, null, defSpecies, null, emptyList()),
        Plant(2, "Sunflower", "Sunflower", defGenus, null, defSpecies, null,emptyList()),
        Plant(3, "Rose", "Sunflower", defGenus, null, defSpecies, null, emptyList()),
        Plant(4, "Rose", "Sunflower", defGenus, null, defSpecies, null, emptyList()),
        Plant(5, "Rose", "Sunflower", defGenus, null, defSpecies, null, emptyList()),
        Plant(6, "Rose", "Sunflower", defGenus, null, defSpecies, null, emptyList())
    )
    override fun getPlants(): Single<List<Plant>> {
        return Single.create {
                emitter -> emitter.onSuccess(allPlants)
        }
    }

    override fun getPlant(id: Int): Single<Plant> {
        return Single.create { emitter ->
            allPlants.find { it.id == id }
                ?.let { emitter.onSuccess(it) }
                ?: emitter.onError(IllegalArgumentException("wrong id: $id"))
        }
    }
}