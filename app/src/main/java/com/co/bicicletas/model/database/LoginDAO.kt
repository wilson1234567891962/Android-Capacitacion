package com.co.bicicletas.model.database

import androidx.room.*
import com.co.bicicletas.model.entities.database.LoginDatabase
import kotlinx.coroutines.flow.Flow

@Dao
interface LoginDAO {

    @Insert
    suspend fun insertUser(login: LoginDatabase)

    @Update
    suspend fun updateUser(login: LoginDatabase)

    @Delete
    suspend fun deleteUser(login: LoginDatabase)

    @Query("Select * from login_table where id=1")
    fun getUserById() : Flow<List<LoginDatabase>>

}