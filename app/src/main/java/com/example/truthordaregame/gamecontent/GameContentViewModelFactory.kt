package com.example.truthordaregame.gamecontent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class GameContentViewModelFactory(private val content: String, private val contentType: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameContentViewModel::class.java)){
            return GameContentViewModel(content, contentType) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}