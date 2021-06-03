package com.co.bicicletas.model.entities.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "login_table")
data class LoginDatabase (
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo val mail: String,
    @ColumnInfo val pass: String,
    @ColumnInfo val state:Boolean
        ):Parcelable