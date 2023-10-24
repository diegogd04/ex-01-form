package com.example.proyectoprueba.features.ex02.burguer.data.remote.api

import retrofit2.Response
import retrofit2.http.GET


interface BurguerApiService {

    //"https://dam.sitehub.es(curso-2023-2024/api/"
    @GET("burguer-view.json")
    suspend fun getBurguer(): Response<BurguerApiModel>
}