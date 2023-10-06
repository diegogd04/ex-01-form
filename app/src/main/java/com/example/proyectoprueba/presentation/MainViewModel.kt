package com.example.proyectoprueba.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.proyectoprueba.app.ErrorApp
import com.example.proyectoprueba.domain.GetUserUseCase
import com.example.proyectoprueba.domain.SaveUserUseCase
import com.example.proyectoprueba.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun saveUser(name: String, surname: String, age: Int){
        saveUserUseCase(SaveUserUseCase.Input(name, surname, age)).fold(
            {responseError(it)},
            {responseSuccess(it)}
        )
    }

    fun loadUser(){
        viewModelScope.launch(Dispatchers.IO){
            getUserUseCase().fold(
                {responseError(it)},
                {responseGetUserSuccess(it)}
            )
        }
    }

    private fun responseError(ErrorApp: ErrorApp){

    }
    private fun responseSuccess(isOk: Boolean){

    }

    private fun responseGetUserSuccess(user: User){
        _uiState.postValue(UiState(user = user))
    }
    data class UiState(
        val ErrorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val user: User? = null
    )
}