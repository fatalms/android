package com.zlogene.diabeticdiray.ui.screen.list.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zlogene.diabeticdiray.database.RecordingDAO
import java.lang.IllegalArgumentException

class DetailViewModelFactory(
    private val dao: RecordingDAO,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
