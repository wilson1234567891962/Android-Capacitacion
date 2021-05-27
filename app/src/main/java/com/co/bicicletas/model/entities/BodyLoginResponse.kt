package com.co.bicicletas.model.entities

object BodyLoginResponse {
    data class LoginResponseDTO(
    val `data`: Data
)

data class Data(
    val idUser: Int,
    val token: String
)
}