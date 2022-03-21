package com.example.truthordaregame.editcontent

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.truthordaregame.database.TruthOrDareGameDatabaseDao

class EditContentViewModelFactory(private val dao: TruthOrDareGameDatabaseDao,
                                  private val application: Application,
                                  private val contentID: Int,
                                  private val contentString: String,
                                  private val contentType: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditContentViewModel::class.java)) {
            return EditContentViewModel(dao, application, contentID, contentString, contentType) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}