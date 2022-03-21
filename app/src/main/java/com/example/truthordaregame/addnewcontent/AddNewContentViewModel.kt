package com.example.truthordaregame.addnewcontent

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.truthordaregame.database.Dare
import com.example.truthordaregame.database.Question
import com.example.truthordaregame.database.TruthOrDareGameDatabaseDao
import kotlinx.coroutines.*

class AddNewContentViewModel(val dao: TruthOrDareGameDatabaseDao, application: Application, contentType: Int) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    private val _contentType = MutableLiveData<Int>()
    val contentType: LiveData<Int>
        get() = _contentType

    init {
        _contentType.value = contentType
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onAddQuestion(questionString: String) {
        uiScope.launch {
            val newQuestion = Question(questionString = questionString, isCustom = 1)
            insertQuestion(newQuestion)
        }
    }

    private suspend fun insertQuestion(question: Question) {
        withContext(Dispatchers.IO) {
            dao.insertQuestion(question)
        }
    }

    fun onAddDare(dareString: String) {
        uiScope.launch {
            val newDare = Dare(dareString = dareString, isCustom = 1)
            insertDare(newDare)
        }
    }

    private suspend fun insertDare(dare: Dare) {
        withContext(Dispatchers.IO) {
            dao.insertDare(dare)
        }
    }
}