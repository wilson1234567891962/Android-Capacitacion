package com.co.bicicletas.model.database

import androidx.room.*
import com.co.bicicletas.model.entities.database.Login
import kotlinx.coroutines.flow.Flow

@Dao
interface LoginDAO {
    @Insert
    suspend fun insertUser(login: Login)

    @Update
    suspend fun updateUser(login: Login)

    @Delete
    suspend fun deleteUser(login: Login)

    @Query("select * from login_table WHERE id = 1")
    fun getUserById(): Flow<List<Login>>

}