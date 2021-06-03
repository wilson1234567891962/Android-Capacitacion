package com.co.bicicletas.model.database

import androidx.annotation.WorkerThread

import com.co.bicicletas.model.entities.database.LoginDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class LoginRepository(private val LoginDao:LoginDao) {
    @WorkerThread
    suspend fun insertUser(login: LoginDatabase) {
        LoginDao.insertUser(login)
    }

    @WorkerThread
    suspend fun updateUser(login: LoginDatabase) {
        LoginDao.updateUser(login)
    }

    @WorkerThread
    suspend fun deleteUser(login: LoginDatabase){
        LoginDao.deleteUser(login)
    }

/*
    @WorkerThread
    suspend fun getAllUser(): Flow<List<LoginDatabase>>{
        return LoginDao.getAllUser()
    }
*/


}