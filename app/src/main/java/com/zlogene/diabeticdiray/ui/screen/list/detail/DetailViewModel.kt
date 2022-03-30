package com.zlogene.diabeticdiray.ui.screen.list.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.zlogene.diabeticdiray.database.RecordingDAO
import com.zlogene.diabeticdiray.model.RecordingEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(
    val dao: RecordingDAO,
    application: Application
) : AndroidViewModel(application) {

    fun updateRecord(record: RecordingEntity) {
        viewModelScope.launch {
            update(record)
        }
    }

    private suspend fun update(record: RecordingEntity) {
        withContext(Dispatchers.IO) {
            dao.update(record)
        }
    }

    fun deleteRecord(record: RecordingEntity) {
        viewModelScope.launch {
            delete(record)
        }
    }

    private suspend fun delete(record: RecordingEntity) {
        withContext(Dispatchers.IO) {
            dao.delete(record)
        }
    }
}
