package com.example.truthordaregame.gamecontent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.truthordaregame.ContentType
import java.lang.IllegalArgumentException

class GameContentViewModelFactory(private val content: String, private val contentType: ContentType): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameContentViewModel::class.java)){
            return GameContentViewModel(content, contentType) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}