package com.co.bicicletas.application

import android.app.Application
import com.co.bicicletas.model.database.LoginRepository
import com.co.bicicletas.model.database.LoginRoomDatabase

class BicicletasApplications : Application(){
    private val database by lazy{LoginRoomDatabase.getDatabase(this@BicicletasApplications)}

    val repository by lazy { LoginRepository(database.loginDao()) }
}