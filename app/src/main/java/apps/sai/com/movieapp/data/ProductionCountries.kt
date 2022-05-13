package com.example.example

import com.google.gson.annotations.SerializedName


data class ProductionCountries(

    @SerializedName("iso_3166_1") val iso31661: String? = null,
    @SerializedName("name") val name: String? = null

)