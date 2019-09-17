package com.tivo.aacomponent.model

data class PlantListJson(
    val id: Int,
    val slug: String,
    val scientific_name: String,
    val link: String,
    val complete_data: Boolean,
    val common_name: String?
)

