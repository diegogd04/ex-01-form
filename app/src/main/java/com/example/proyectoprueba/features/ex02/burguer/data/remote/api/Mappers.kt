package com.example.proyectoprueba.features.ex02.burguer.data.remote.api

import com.example.proyectoprueba.features.ex02.burguer.domain.Burguer

fun BurguerApiModel.toModel(): Burguer =
    Burguer("1", this.urlImage, this.title, this.like, this.time)