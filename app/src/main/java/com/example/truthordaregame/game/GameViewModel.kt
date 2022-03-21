package com.example.truthordaregame.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    private var _currentPair = MutableLiveData<Pair<String, String>>(Pair("",""))
    val currentPair: LiveData<Pair<String, String>>
        get() = _currentPair

    lateinit var generalPairList: MutableList<Pair<String, String>>

    init {
        resetList()
        nextPair()

        Log.i("GameViewModel", "GameViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed")
    }

    private fun resetList(){
        generalPairList = mutableListOf<Pair<String, String>>(Pair("Ты гей?", "Пососи мой соск"),Pair("ЛОХ????", "ПОШЕЛ нахЙУ"))
    }

    fun nextPair(){
        if (generalPairList.isNotEmpty()) {
            _currentPair.value = generalPairList.removeLast()

        }
        else
            //ResetList
            Log.i("GameViewModel", "The list is empty!")
    }
}