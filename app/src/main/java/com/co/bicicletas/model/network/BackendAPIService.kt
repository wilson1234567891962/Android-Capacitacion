package com.co.bicicletas.model.network

import android.util.Log
import com.co.bicicletas.model.entities.BodyLogin
import com.co.bicicletas.model.entities.BodyRemember
import com.co.bicicletas.model.entities.ResponseLogin
import com.co.bicicletas.model.entities.ResponseRemember
import com.co.bicicletas.utils.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BackendAPIService {

    private val api = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL) // Set the API base URL.
        // Add converter factory for serialization and deserialization of objects.
        /**
         * A Converter.Factory converter which uses Gson for JSON.
         *
         * Because Gson is so flexible in the types it supports, this converter assumes that it can handle
         * all types.
         */
        .addConverterFactory(GsonConverterFactory.create())
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
        .create(BicicletasAPIS::class.java)

    fun doLogin(body : BodyLogin): Single<ResponseLogin> {
        return api.login(body)
    }

    fun doRemember(body: BodyRemember) : Single<ResponseRemember>{
        return api.remember(body)
    }
}