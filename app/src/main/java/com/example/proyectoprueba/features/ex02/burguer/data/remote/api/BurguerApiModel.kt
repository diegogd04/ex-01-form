package com.example.proyectoprueba.features.ex02.burguer.data.remote.api

import com.google.gson.annotations.SerializedName

data class BurguerApiModel(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("url_image") val urlImage: String,
    @SerializedName("rate") val like: String,
    @SerializedName("eta") val time: String
)