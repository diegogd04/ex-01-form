package com.example.proyectoprueba.features.ex02.burguer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoprueba.app.ErrorApp
import com.example.proyectoprueba.features.ex02.burguer.domain.Burguer
import com.example.proyectoprueba.features.ex02.burguer.domain.GetBurguerUseCase
import com.example.proyectoprueba.features.ex02.burguer.domain.SaveBurguerUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Ex02BurguerMainViewModel (
    private val saveBurguerUseCase: SaveBurguerUseCase,
    private val getBurguerUseCase : GetBurguerUseCase
) : ViewModel(){
    private val _uiState = MutableLiveData<UiState>()
    val uiState : LiveData<UiState> = _uiState

    fun saveBurguer(id: Int, discount: String, description: String, like: String, time: String){
        saveBurguerUseCase(SaveBurguerUseCase.Input(id, discount, description, like, time)).fold(
            {responseError(it)},
            {responseSuccess(it)}
        )
    }

    fun loadBurguer(){
        viewModelScope.launch(Dispatchers.IO){
            getBurguerUseCase().fold(
                {responseError(it)},
                {responseGetBurguerSuccess(it)}
            )
        }
    }

    private fun responseError(ErrorApp: ErrorApp?) {

    }

    private fun responseSuccess(isOk: Boolean) {

    }

    private fun responseGetBurguerSuccess(burguer: Burguer) {
        _uiState.postValue(UiState(burguer = burguer))
    }

    data class UiState(
        val ErrorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val burguer: Burguer? = null
    )
}