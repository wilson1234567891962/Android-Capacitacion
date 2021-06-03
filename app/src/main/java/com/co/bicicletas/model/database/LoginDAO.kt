package com.co.bicicletas.model.database

import androidx.room.*
import com.co.bicicletas.model.entities.database.LoginDatabase
import kotlinx.coroutines.flow.Flow


@Dao
interface LoginDAO {

    @Insert
    suspend fun insertLoginData(login: LoginDatabase)

    @Update
    suspend fun updateLoginData(login:LoginDatabase)

    @Delete
    suspend fun deleteLoginData(login:LoginDatabase)

  /*  @Query("SELECT * from login_table")
    suspend fun  queryLoginData() : Flow<List<LoginDatabase>>*/


}