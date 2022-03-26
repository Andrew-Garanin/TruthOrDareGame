package com.example.truthordaregame.game

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.truthordaregame.database.TruthOrDareGameDatabaseDao

class GameViewModelFactory(private val dao: TruthOrDareGameDatabaseDao,
                           private val application: Application,) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}