package com.example.truthordaregame.gamecontent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameContentViewModel(content: String, contentType: Int) : ViewModel() {
    private val _content = MutableLiveData<String>()
    val content: LiveData<String>
        get() = _content

    private val _contentType = MutableLiveData<Int>()
    val contentType: LiveData<Int>
        get() = _contentType

    init {
        _content.value = content
        _contentType.value = contentType
    }
}