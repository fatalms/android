package com.zlogene.diabeticdiray.ui.screen.addrecord

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zlogene.diabeticdiray.database.RecordingDAO
import java.lang.IllegalArgumentException

class AddRecordViewModelFactory(
    private val dao: RecordingDAO,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddRecordViewModel::class.java)) {
            return AddRecordViewModel(dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
