package com.example.truthordaregame.editcontent

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.truthordaregame.database.Dare
import com.example.truthordaregame.database.Question
import com.example.truthordaregame.database.TruthOrDareGameDatabaseDao
import kotlinx.coroutines.*

class EditContentViewModel(val dao: TruthOrDareGameDatabaseDao, application: Application, contentID: Int, contentString: String, contentType: Int) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    private val _contentType = MutableLiveData<Int>()
    val contentType: LiveData<Int>
        get() = _contentType

    private val _contentID = MutableLiveData<Int>()
    val contentID: LiveData<Int>
        get() = _contentID

    private val _contentString = MutableLiveData<String>()
    val contentString: LiveData<String>
        get() = _contentString

    init {
        _contentType.value = contentType
        _contentID.value = contentID
        _contentString.value = contentString
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onUpdateQuestion(updatedQuestionString: String) {
        uiScope.launch {
            val updatedQuestion = Question(_contentID.value!!, questionString = updatedQuestionString, isCustom = 1)
            updateQuestion(updatedQuestion)
        }
    }

    private suspend fun updateQuestion(question: Question) {
        withContext(Dispatchers.IO) {
            dao.updateQuestion(question)
        }
    }

    fun onUpdateDare(updatedDareString: String) {
        uiScope.launch {
            val updatedDare = Dare(_contentID.value!!, dareString = updatedDareString, isCustom = 1)
            updateDare(updatedDare)
        }
    }

    private suspend fun updateDare(dare: Dare) {
        withContext(Dispatchers.IO) {
            dao.updateDare(dare)
        }
    }
}