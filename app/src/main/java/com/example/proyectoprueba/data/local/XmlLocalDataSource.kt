package com.example.proyectoprueba.data.local

import android.content.Context
import com.example.proyectoprueba.app.ErrorApp
import com.example.proyectoprueba.domain.SaveUserUseCase
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right

class XmlLocalDataSource (private val context: Context){

    private val sharedPref = context.getSharedPreferences("users", Context.MODE_PRIVATE)

    fun saveUser(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean>{
        return try{
            with(sharedPref.edit())
                putString("id", (1..100).random())
                putString("username", username)
                putString("surname", surname)
                putString("age", age)
                apply()

        try{
            with(sharedPref.edit()){
                putInt("id", (1..100).random())
                putString("username", username)
                putString("surname", surname)
                putInt("age", age)
                apply()
            }
            return true.right()
        }catch (ex: Exception){
            return ErrorApp.UncknowError.left()
        }
    }

}