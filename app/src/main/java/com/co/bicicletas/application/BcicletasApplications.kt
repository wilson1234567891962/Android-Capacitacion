package com.co.bicicletas.application

import android.app.Application
import com.co.bicicletas.model.database.LoginDao
import com.co.bicicletas.model.database.LoginRepository
import com.co.bicicletas.model.database.LoginRoomDatabase

class BcicletasApplications : Application() {

//    override fun onCreate() {
//        super.onCreate()
//
//    }

private val database by lazy { LoginRoomDatabase.getDatabase(this@BcicletasApplications) }
val repository by lazy { LoginRepository(database.loginDao()) }
}