package com.tivo.aacomponent.model

import com.google.gson.annotations.SerializedName

data class Species(
    val id: Int,
    @SerializedName("common_name")
    val name: String?,
    val scientific_name: String,
    val is_main_species: Boolean
)
