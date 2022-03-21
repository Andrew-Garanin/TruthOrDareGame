package com.example.truthordaregame.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
data class Question(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name ="questionID")
    var questionID: Int = 0,

    @ColumnInfo(name = "questionString")
    @NonNull
    val questionString: String = "",

    @ColumnInfo(name = "isCustom") // 0 - дефолтный, 1 - пользовательский
    @NonNull
    val isCustom: Int = 0
)
