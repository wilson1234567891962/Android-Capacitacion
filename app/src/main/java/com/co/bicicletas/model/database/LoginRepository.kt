package com.co.bicicletas.model.database

import androidx.annotation.WorkerThread

import com.co.bicicletas.model.entities.database.Login
import kotlinx.coroutines.flow.Flow

class LoginRepository(
    private val LoginDao: LoginDao
) {
    @WorkerThread
    suspend fun insertUser(login: Login){

        LoginDao.insertUser(login)

    }

    @WorkerThread
    suspend fun updateUser(login: Login){
        LoginDao.updateUser(login)
    }

    @WorkerThread
    suspend fun deletetUser(login: Login){
        LoginDao.deletetUser(login)
    }


}