package com.example.truthordaregame.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Question::class, Dare::class], version = 1, exportSchema = false)
abstract class TruthOrDareGameDatabase: RoomDatabase() {

    abstract fun getTruthOrDareGameDatabaseDao(): TruthOrDareGameDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: TruthOrDareGameDatabase? = null

        fun getInstance(context: Context): TruthOrDareGameDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        TruthOrDareGameDatabase::class.java, "truth_dare_game_db").createFromAsset("database/truth_dare_game_db.db")
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}