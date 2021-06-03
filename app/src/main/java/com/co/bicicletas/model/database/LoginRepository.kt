package com.co.bicicletas.model.database

import androidx.annotation.WorkerThread
import com.co.bicicletas.model.entities.database.LoginDatabase
import kotlinx.coroutines.flow.Flow

class LoginRepository(
        private val loginDAO : LoginDAO
) {

    @WorkerThread
    suspend fun insertUser(login: LoginDatabase){
        loginDAO.insertUser(login)
    }

    @WorkerThread
    suspend fun updateUser(login: LoginDatabase){
        loginDAO.updateUser(login)
    }

    @WorkerThread
    suspend fun deleteUser(login: LoginDatabase){
        loginDAO.deleteUser(login)
    }

    fun getUserById() : Flow<List<LoginDatabase>>{
        return loginDAO.getUserById()
    }
}