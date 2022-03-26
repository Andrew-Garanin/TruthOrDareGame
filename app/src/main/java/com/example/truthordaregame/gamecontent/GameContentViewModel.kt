package com.example.truthordaregame.gamecontent

import android.os.CountDownTimer
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

    private var _eventGameFinish = MutableLiveData(false)
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    private var _secondsUntilEnd = MutableLiveData<Long>()
    val secondsUntilEnd : LiveData<Long>
        get() = _secondsUntilEnd

    private var _isTimerPaused = MutableLiveData(false)
    val isTimerPaused : LiveData<Boolean>
        get() = _isTimerPaused

    //private var timer: CountDownTimer

    companion object{
        const val ONE_SECOND = 1000L
        const val GAME_TIME = 10 * ONE_SECOND
    }

    init {
        _content.value = content
        _contentType.value = contentType
        _secondsUntilEnd.value = GAME_TIME / ONE_SECOND

       // timer(_secondsUntilEnd.value!! * ONE_SECOND).start()
    }

    private fun timer(millisInFuture:Long):CountDownTimer{
        return object: CountDownTimer(millisInFuture, ONE_SECOND){
            override fun onTick(millisUntilFinished: Long){
                _secondsUntilEnd.value = millisUntilFinished / ONE_SECOND

                if (_isTimerPaused.value == true){
                    cancel()
                }
            }

            override fun onFinish() {
                _eventGameFinish.value = true
            }
        }
    }
    fun pauseTimer(){
        _isTimerPaused.value = true
    }
    fun resumeTimer(){
        _isTimerPaused.value = false
        timer(_secondsUntilEnd.value!! * ONE_SECOND).start()
    }
}