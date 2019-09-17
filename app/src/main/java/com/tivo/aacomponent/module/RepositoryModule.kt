package com.tivo.aacomponent.module

import com.tivo.aacomponent.repository.PlantRepository
import com.tivo.aacomponent.repository.PlantServiceRepository
import com.tivo.aacomponent.repository.SamplePlantDataRepository
import org.koin.dsl.module

class RepositoryModule {

    companion object {
        fun create() = module {
            single<PlantRepository> { PlantServiceRepository() }
        }
    }
}