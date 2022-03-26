package com.example.truthordaregame.game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.truthordaregame.database.TruthOrDareGameDatabaseDao

class GameViewModel(val dao: TruthOrDareGameDatabaseDao, application: Application): AndroidViewModel(application) {

    var questions = dao.getAllQuestions()
    var dares = dao.getAllDares()
    var generalPairList = MutableLiveData<MutableList<Pair<String, String>>>(arrayListOf())

    private var _currentPair = MutableLiveData(Pair("",""))
    val currentPair: LiveData<Pair<String, String>>
        get() = _currentPair

    fun resetList(){
        val dareList : MutableList<String> = arrayListOf()
        val questionList : MutableList<String> = arrayListOf()

        for(dare in dares.value!!)
            dareList.add(dare.dareString)
        for(question in questions.value!!)
            questionList.add(question.questionString)

        dareList.shuffle()
        questionList.shuffle()

        val shuffledPairs: MutableList<Pair<String, String>> = arrayListOf()
        questionList.zip(dareList).forEach { pair ->
            shuffledPairs.add(pair)
        }
        generalPairList.value = shuffledPairs
    }

    fun nextPair(){
        _currentPair.value = generalPairList.value?.removeLast()
    }
}
