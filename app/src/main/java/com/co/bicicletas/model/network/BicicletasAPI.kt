package com.co.bicicletas.model.network

import com.co.bicicletas.model.entities.BodyLoginResponse
import com.co.bicicletas.model.entities.LoginDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface BicicletasAPI {
    @POST("/UMB/login/")
    fun login(@Body body: LoginDTO) : Single<BodyLoginResponse>

}