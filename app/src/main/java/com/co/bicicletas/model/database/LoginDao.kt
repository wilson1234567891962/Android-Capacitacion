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

    @Query("select * from login_table WHERE id = 1")
    fun getUserbyId(): Flow<List<Login>>

}