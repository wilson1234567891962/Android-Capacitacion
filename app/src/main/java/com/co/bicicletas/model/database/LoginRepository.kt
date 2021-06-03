package com.co.bicicletas.model.database

import androidx.annotation.WorkerThread

import com.co.bicicletas.model.entities.database.LoginDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class LoginRepository(

    private val loginDatabase:LoginDAO
){
    @WorkerThread
    suspend fun insertLoginData(login: LoginDatabase){
        loginDatabase.insertLoginData(login)

    }

    @WorkerThread
    suspend fun updateLoginData(login: LoginDatabase){
        loginDatabase.updateLoginData(login)

    }

    @WorkerThread
    suspend fun deleteLoginData(login: LoginDatabase){
        loginDatabase.deleteLoginData(login)


    }

   /* @WorkerThread
    suspend fun  queryLoginData() : Flow<List<LoginDatabase>>{
        return loginDatabase.queryLoginData()


    }*/
}