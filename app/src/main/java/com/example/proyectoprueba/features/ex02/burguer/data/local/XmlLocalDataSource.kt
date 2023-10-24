package com.example.proyectoprueba.features.ex02.burguer.data.local

import android.content.Context
import com.example.proyectoprueba.app.ErrorApp
import com.example.proyectoprueba.features.ex02.burguer.domain.Burguer
import com.example.tapas_diego_unai.app.serialization.JsonSerialization
import com.google.gson.Gson
import com.google.gson.JsonSerializer
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right

class XmlLocalDataSource (
    private val context: Context,
    private val serialization: JsonSerialization
){
    private val sharedPref = context.getSharedPreferences("burguers", Context.MODE_PRIVATE)
    private val burguerId = "1"

    fun saveBurguer(burguer: Burguer): Either<ErrorApp, Boolean> {
        sharedPref.edit().apply() {
            putString(burguerId, serialization.toJson(burguer, Burguer::class.java))
            apply()
        }
        return ErrorApp.UncknowError.left()
    }

    fun findBurguer(): Either<ErrorApp, Burguer>{
            val jsonBurguer = sharedPref.getString(burguerId, null)
            jsonBurguer?.let {
                return serialization.fromJson(it, Burguer::class.java).right()
            }
            return ErrorApp.UncknowError.left()
    }
}