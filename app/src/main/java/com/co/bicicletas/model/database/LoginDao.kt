package com.co.bicicletas.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query
import com.co.bicicletas.model.entities.database.LoginDatabase
import kotlinx.coroutines.flow.Flow

@Dao
interface LoginDao {
    @Insert
    suspend fun insertUser(login: LoginDatabase)

    @Update
    suspend fun updateUser(login: LoginDatabase)

    @Delete
    suspend fun deleteUser(login: LoginDatabase)


    @Query("select * from login_table")
    fun getAllUser(): Flow<List<LoginDatabase>>



}