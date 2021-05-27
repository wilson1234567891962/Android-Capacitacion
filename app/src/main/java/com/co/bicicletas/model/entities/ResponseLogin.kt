package com.co.bicicletas.model.entities

class ResponseLogin(
    val data: Data
) {

data class Data(
    val idUser: Int,
    val token: String
)
}