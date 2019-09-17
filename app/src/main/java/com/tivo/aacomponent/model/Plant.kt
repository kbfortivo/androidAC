package com.tivo.aacomponent.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Plant(
    val id: Int,
    val common_name: String?,
    val scientific_name: String,
    val genus: Genus?,
    val family_common_name: String?,
    @SerializedName("main_species")
    val species: Species,
    val order: Order?,
    val images: List<Image>
) {
    data class Image(
        val url: String
    )
}