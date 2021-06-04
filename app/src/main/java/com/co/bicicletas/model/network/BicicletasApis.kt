package com.co.bicicletas.model.network

import com.co.bicicletas.model.entities.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BicicletasApis {



    @POST("UMB/login/")
    fun login(@Body loginBody : LoginDTO  ) : Single<BodyLoginResponse.LoginResponseDTO>

    @POST("UMB/forgetPassword/")
    fun forgot(@Body forgotBody : ForgotDTO) : Single<ForgotResponseDTO>

    @GET("albums")
    fun getAlbums() : Single<AlbumsResponseDTO>





}