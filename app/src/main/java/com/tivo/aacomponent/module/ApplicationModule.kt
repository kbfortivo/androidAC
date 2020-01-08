package com.tivo.aacomponent.module

import com.tivo.aacomponent.MainActivity
import com.tivo.aacomponent.PlantDetailsActivity
import com.tivo.aacomponent.repository.PlantRepository
import com.tivo.aacomponent.repository.PlantServiceRepository
import com.tivo.aacomponent.viewmodel.PlantDetailsViewModel
import com.tivo.aacomponent.viewmodel.PlantListViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

class ApplicationModule {

    companion object {
        fun create() = module {
            scope(named<MainActivity>())  {
                scoped { PlantListViewModel(get()) }
            }

            scope((named<PlantDetailsActivity>())) {
                scoped { PlantDetailsViewModel(get(), getProperty("activityContext")) }
            }

            single<PlantRepository> { PlantServiceRepository() }
        }
    }
}