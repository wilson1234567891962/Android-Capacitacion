package com.co.bicicletas.model.network

import com.co.bicicletas.model.entities.BodyResponse
import com.co.bicicletas.model.entities.LoginDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

abstract class BiclicateAPIs {

    @POST("UMB/login/")
    fun login(@Body b:LoginDTO): Single<BodyResponse>
}