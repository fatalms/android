package com.zlogene.diabeticdiray.ui.screen.list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zlogene.diabeticdiray.database.RecordingDAO
import java.lang.IllegalArgumentException

class ListViewModelFactory(
    private val dao: RecordingDAO,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
