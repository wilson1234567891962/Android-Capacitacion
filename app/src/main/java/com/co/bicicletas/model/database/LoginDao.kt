package com.co.bicicletas.model.database

import androidx.room.*
import com.co.bicicletas.model.entities.database.Login
import kotlinx.coroutines.flow.Flow


    //para decir que se va a implementar
    @Dao
     interface LoginDao {

        @Insert
        suspend fun inserUser(login:Login)

        @Update
        suspend fun updateUser(login:Login)

        @Delete
        suspend fun deleteUser(login:Login)

        //devuleve un array
        @Query("SELECT  * FROM login_table where id = 1")
         fun getUserById() : Flow<List<Login>>

}