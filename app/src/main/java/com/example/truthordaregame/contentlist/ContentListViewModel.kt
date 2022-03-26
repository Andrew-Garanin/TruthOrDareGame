package com.example.truthordaregame.contentlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.truthordaregame.database.TruthOrDareGameDatabaseDao
import kotlinx.coroutines.*

class ContentListViewModel(val dao: TruthOrDareGameDatabaseDao, application: Application, contentType: Int) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    private val _contentType = MutableLiveData<Int>()
    val contentType: LiveData<Int>
        get() = _contentType

    val questions = dao.getUserQuestions()
    val dares = dao.getUserDares()

    init {
        _contentType.value = contentType
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onDeleteQuestion(id: Int){
        uiScope.launch {
            removeQuestion(id)
        }
    }

    private suspend fun removeQuestion(id: Int) {
        withContext(Dispatchers.IO) {
            dao.deleteQuestion(id)
        }
    }

    fun onDeleteDare(id: Int){
        uiScope.launch {
            removeDare(id)
        }
    }

    private suspend fun removeDare(id: Int) {
        withContext(Dispatchers.IO) {
            dao.deleteDare(id)
        }
    }
}
