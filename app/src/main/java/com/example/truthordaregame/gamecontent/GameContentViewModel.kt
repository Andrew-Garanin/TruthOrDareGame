package com.example.truthordaregame.gamecontent

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameContentViewModel(content: String) : ViewModel() {
    private val _content = MutableLiveData<String>()
    val content: LiveData<String>
        get() = _content

    init {
        _content.value = content
    }
}