package com.co.bicicletas.model.entities.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "login_table")
data class Login(@PrimaryKey(autoGenerate = true) var id: Int = 0, @ColumnInfo var mail:String, @ColumnInfo var passw :String, @ColumnInfo var state:Boolean) : Parcelable
