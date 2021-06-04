package com.co.bicicletas.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.co.bicicletas.model.entities.database.Login


@Database(entities = [Login::class], version = 1)
abstract class LoginRoomDatabase : RoomDatabase(){

abstract fun loginDao() : LoginDao

//realiza el singleton de las n variables que hagamosm, y quedan en referencia y alas instancia
    companion object {

    @Volatile
    //?= en caso de que no este inicializada nos devuleve un null
        private var INSTANCE : LoginRoomDatabase?=null


    //el contexto dice quien es el dueño del proceso
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