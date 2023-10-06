package com.example.proyectoprueba.data.local

import android.content.Context
import android.provider.Settings.Global.putString
import android.provider.Settings.Secure.putString
import com.example.proyectoprueba.app.ErrorApp
import com.example.proyectoprueba.domain.SaveUserUseCase
import com.example.proyectoprueba.domain.User
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right

class XmlLocalDataSource (private val context: Context){

    private val sharedPref = context.getSharedPreferences("users", Context.MODE_PRIVATE)

    fun saveUser(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean>{
        return try{
            with(sharedPref.edit()) {
                putInt("id", (1..100).random())
                putString("username", input.username)
                putString("surname", input.surname)
                putInt("age", input.age)
                apply()
            }
            true.right()
        }catch (ex: Exception){
            return ErrorApp.UncknowError.left()
        }
    }

    fun findUser(): Either<ErrorApp, User>{
        return try{
            User(
                sharedPref.getInt("id", 0),
                sharedPref.getString("usernme", "")!!,
                sharedPref.getString("surname", "")!!,
                sharedPref.getInt("age", 0)!!
            ).right()
        }catch (ex:Exception){
            return ErrorApp.UncknowError.left()
        }
    }

}