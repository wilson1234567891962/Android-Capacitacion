package com.co.bicicletas.model.entities.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "login_table")
data class Login(@PrimaryKey(autoGenerate = true) val id: Int = 0,@ColumnInfo val mail:String,@ColumnInfo val passw :String,@ColumnInfo val state:Boolean) : Parcelable
