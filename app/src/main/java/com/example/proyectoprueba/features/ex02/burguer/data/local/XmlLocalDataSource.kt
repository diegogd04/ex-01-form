package com.example.proyectoprueba.features.ex02.burguer.data.local

import android.content.Context
import com.example.proyectoprueba.app.ErrorApp
import com.example.proyectoprueba.features.ex01.domain.SaveUserUseCase
import com.example.proyectoprueba.features.ex01.domain.User
import com.example.proyectoprueba.features.ex02.burguer.domain.Burguer
import com.example.proyectoprueba.features.ex02.burguer.domain.SaveBurguerUseCase
import com.google.gson.Gson
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right

class XmlLocalDataSource (private val context: Context){
    private val sharedPref = context.getSharedPreferences("burguers", Context.MODE_PRIVATE)

    private val gson = Gson()

    fun saveBurguer(input: SaveBurguerUseCase.Input): Either<ErrorApp, Boolean>{
        return try{
            with(sharedPref.edit()) {
                val burguer = Burguer(input.id, input.discount, input.description, input.like, input.time)
                val jsonBurguer = gson.toJson(burguer, Burguer::class.java)
                putString(input.id.toString(), jsonBurguer)
                apply()
            }
            true.right()
        }catch (ex: Exception){
            return ErrorApp.UncknowError.left()
        }
    }

    fun findBurguer(burguerId: Int = 0): Either<ErrorApp, Burguer>{
        return try{
            val jsonBurguer = sharedPref.getString(burguerId.toString(), "{}")
            return gson.fromJson(jsonBurguer, Burguer::class.java).right()
        }catch (ex:Exception){
            return ErrorApp.UncknowError.left()
        }
    }
}