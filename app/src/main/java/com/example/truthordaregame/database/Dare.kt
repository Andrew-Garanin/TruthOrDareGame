package com.example.truthordaregame.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dare")
data class Dare(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name ="dareID")
    var dareID: Int = 0,

    @ColumnInfo(name = "dareString")
    @NonNull
    val dareString: String = "",

    @ColumnInfo(name = "isCustom") // 0 - дефолтный, 1 - пользовательский
    @NonNull
    val isCustom: Int = 0
)