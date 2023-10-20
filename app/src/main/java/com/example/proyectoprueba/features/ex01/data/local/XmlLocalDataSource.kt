package com.example.proyectoprueba.features.ex01.data.local

import android.content.Context
import android.provider.Settings.Global.putString
import android.provider.Settings.Secure.putString
import com.example.proyectoprueba.app.ErrorApp
import com.example.proyectoprueba.features.ex01.domain.SaveUserUseCase
import com.example.proyectoprueba.features.ex01.domain.User
import com.google.gson.Gson
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right

class XmlLocalDataSource (private val context: Context){

    private val sharedPref = context.getSharedPreferences("users", Context.MODE_PRIVATE)

    private val gson = Gson()

    init{
        sharedPref.edit().remove("users").commit()
    }

    fun saveUser(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean>{
        return try{
            with(sharedPref.edit()) {
                val id = (1..100).random()
                val user = User(id, input.username, input.surname, input.age)
                val jsonUser = gson.toJson(user, User::class.java)
                putString(id.toString(), jsonUser)
                apply()
            }
            true.right()
        }catch (ex: Exception){
            return ErrorApp.UncknowError.left()
        }
    }

    fun findUser(userId: Int=0): Either<ErrorApp, User>{
        return try{
            val jsonUser = sharedPref.getString(userId.toString(), "{}")
            return gson.fromJson(jsonUser, User::class.java).right()
        }catch (ex:Exception){
            return ErrorApp.UncknowError.left()
        }
    }

    fun removeUserById(userId: Int){
        sharedPref.edit().remove(userId.toString()).apply()
    }
}