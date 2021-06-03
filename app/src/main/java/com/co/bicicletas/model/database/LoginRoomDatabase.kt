package com.co.bicicletas.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.co.bicicletas.model.entities.database.Login

@Database(entities = [Login::class], version = 1)
abstract class LoginRoomDatabase : RoomDatabase(){

    abstract fun loginDAO() : LoginDAO

    companion object {

        @Volatile
        private var INSTANCE: LoginRoomDatabase? = null


        fun getDatabase(context: Context): LoginRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LoginRoomDatabase::class.java,
                    "music_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}