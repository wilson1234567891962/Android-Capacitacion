package com.co.bicicletas.model.network

import com.co.bicicletas.model.entities.BodyLoginResponse
import com.co.bicicletas.model.entities.BodyPassResponse
import com.co.bicicletas.model.entities.LoginDTO
import com.co.bicicletas.model.entities.PassDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface BicicletasAPI {
    @POST("/UMB/login/")
    fun login(@Body body: LoginDTO) : Single<BodyLoginResponse.LoginResponseDTO>

    @POST("/UMB/forgetPassword/")
    fun pass(@Body body: PassDTO) : Single<BodyPassResponse>

}