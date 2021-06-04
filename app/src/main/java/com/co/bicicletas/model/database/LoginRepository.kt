package com.co.bicicletas.model.database


import androidx.annotation.WorkerThread
import com.co.bicicletas.model.entities.database.Login
import kotlinx.coroutines.flow.Flow

class LoginRepository (private val loginDAO : LoginDao){



    //proceso ascincrono
    @WorkerThread
    suspend fun inserUser(login:Login) {

        loginDAO.inserUser(login);



    }

    @WorkerThread
    suspend fun updateUser(login:Login) {

        loginDAO.inserUser(login);

    }

    @WorkerThread
    suspend fun deleteUser(login:Login) {

        loginDAO.inserUser(login);

    }


     fun getUserById() : Flow<List<Login>>{
        val state : Flow<Boolean>

        return loginDAO.getUserById();

    }

}