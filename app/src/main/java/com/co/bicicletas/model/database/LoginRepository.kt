package com.co.bicicletas.model.database

import androidx.annotation.WorkerThread
import com.co.bicicletas.model.entities.database.Login
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class LoginRepository (
    private var loginDAO: LoginDAO
) {
    @WorkerThread
    suspend fun insertUser(login: Login) {

        loginDAO.insertUser(login)

    }

    @WorkerThread
    suspend fun updateUser(login: Login) {

        loginDAO.updateUser(login)

    }

    @WorkerThread
    suspend fun deleteUser(login: Login) {

        loginDAO.updateUser(login)

    }

//    @WorkerThread
//    suspend fun getAllUsers() : Flow<List<Login>>{
//        return loginDAO.getAllUsers()
//    }

}