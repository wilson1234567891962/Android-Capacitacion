package com.co.bicicletas.aplication.bicicletas

import android.app.Application
import com.co.bicicletas.model.database.LoginRepository
import com.co.bicicletas.model.database.LoginRoomDatabase

class BicicletasApplication : Application(){

    private val database by lazy { LoginRoomDatabase.getDatabase(this@BicicletasApplication) }
    val Repository by lazy { LoginRepository(database.LoginDao()) }
}