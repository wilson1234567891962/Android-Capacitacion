package com.co.bicicletas.model.database

import androidx.room.*
import com.co.bicicletas.model.entities.database.Login
import kotlinx.coroutines.flow.Flow

@Dao
interface LoginDao {

    @Insert
    suspend fun insertUser(login: Login)

    @Update
    suspend fun updateUser(login: Login)

    @Delete
    suspend fun deletetUser(login: Login)


}