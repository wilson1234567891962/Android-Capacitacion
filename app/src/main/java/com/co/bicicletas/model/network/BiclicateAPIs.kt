package com.co.bicicletas.model.network

import com.co.bicicletas.model.entities.BodyResponse
import com.co.bicicletas.model.entities.LoginDTO
import com.co.bicicletas.model.entities.forgotPassRQ
import com.co.bicicletas.model.entities.forgotPassRS
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface BiclicateAPIs {

    @POST("UMB/login/")
    fun login(@Body loginBody: LoginDTO): Single<BodyResponse.LoginResponseDTO>


    @POST("UMB/forgetPassword/")
    fun forgot(@Body forgotBody: forgotPassRQ): Single<forgotPassRS>

}