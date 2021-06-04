package com.co.bicicletas.model.network

import com.co.bicicletas.model.entities.BodyLoginResponse
import com.co.bicicletas.model.entities.ForgotDTO
import com.co.bicicletas.model.entities.ForgotResponseDTO
import com.co.bicicletas.model.entities.LoginDTO
import com.co.bicicletas.utils.Constants
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class BackendApiService {

    

    fun doLogin(bodyLogin : LoginDTO ) :
         Single<BodyLoginResponse.LoginResponseDTO> {
        return api.login(bodyLogin)
    }

    fun doForgot(bodyForgot : ForgotDTO) : Single<ForgotResponseDTO>{
        return api.forgot(bodyForgot)
    }

    private fun generateOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    fun getInstanceBackend(uri: String):BicicletasApis
    {
        val api = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL) // Set the API base URL.
            // Add converter factory for serialization and deserialization of objects.
            /**
             * A Converter.Factory converter which uses Gson for JSON.
             *
             * Because Gson is so flexible in the types it supports, this converter assumes that it can handle
             * all types.
             */
            .addConverterFactory(GsonConverterFactory.create())
            .client(generateOkHttpClient())
            /**
             * **
             * Add a call adapter factory for supporting service method return types other than.
             *
             * A CallAdapter.Factory call adapter which uses RxJava 3 for creating observables.
             *
             * Adding this class to Retrofit allows you to return an Observable, Flowable, Single, Completable
             * or Maybe from service methods.
             */
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build() // Create the Retrofit instance using the configured values.
            // Create an implementation of the API endpoints defined by the service interface in our case it is RandomDishAPI.
            .create(BicicletasApis::class.java)
        return api
    }



}