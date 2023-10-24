package com.example.proyectoprueba.features.ex02.burguer.data.remote

import com.example.proyectoprueba.app.ErrorApp
import com.example.proyectoprueba.features.ex02.burguer.data.remote.api.BurguerApiModel
import com.example.proyectoprueba.features.ex02.burguer.data.remote.api.BurguerApiService
import com.example.proyectoprueba.features.ex02.burguer.data.remote.api.toModel
import com.example.proyectoprueba.features.ex02.burguer.domain.Burguer
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiMockRemoteDataSource {

    private val baseUrl = "https://dam.sitehub.ies/curso-2023-2024/api/"
    suspend fun getBurguerMock(): Either<ErrorApp, Burguer>{
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: BurguerApiService = retrofit.create(BurguerApiService::class.java)

        try{
            val repos: Response<BurguerApiModel> = service.getBurguer()

            if(repos.isSuccessful){
                return repos.body()!!.toModel().right()
            }else{
                return ErrorApp.UncknowError.left()
            }
        }catch (ex: Exception){
            return ErrorApp.UncknowError.left()
        }
    }

}