package com.co.bicicletas.model.network

import com.co.bicicletas.model.entities.BodyLogin
import com.co.bicicletas.model.entities.ResponseLogin
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface BicicletasAPIS {

    @POST("UMB/login/")
    fun login(@Body login : BodyLogin) : Single<ResponseLogin>
}