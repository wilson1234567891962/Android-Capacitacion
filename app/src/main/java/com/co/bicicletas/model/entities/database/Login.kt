package com.co.bicicletas.model.entities.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "login_table")
data class Login(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val email:String,
    @ColumnInfo val password:String,
    @ColumnInfo val state:String
): Parcelable

