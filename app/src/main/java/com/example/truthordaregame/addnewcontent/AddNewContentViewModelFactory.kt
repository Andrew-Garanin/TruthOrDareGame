package com.example.truthordaregame.addnewcontent

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.truthordaregame.ContentType
import com.example.truthordaregame.database.TruthOrDareGameDatabaseDao

class AddNewContentViewModelFactory(
    private val dao: TruthOrDareGameDatabaseDao,
    private val application: Application,
    private val contentType: ContentType
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNewContentViewModel::class.java)) {
            return AddNewContentViewModel(dao, application, contentType) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}