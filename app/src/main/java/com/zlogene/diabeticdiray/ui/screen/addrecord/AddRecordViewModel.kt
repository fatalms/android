package com.zlogene.diabeticdiray.ui.screen.addrecord

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.zlogene.diabeticdiray.database.RecordingDAO
import com.zlogene.diabeticdiray.model.RecordingEntity
import com.zlogene.diabeticdiray.util.CustomDataConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class AddRecordViewModel(
    val dao: RecordingDAO,
    application: Application
) : AndroidViewModel(application) {

    fun addRecord(sugar: String, insulin: String, note: String) {
        viewModelScope.launch {
            val calendar = Calendar.getInstance()
            val record = RecordingEntity(
                0L,
                CustomDataConverter.getDateUnix(calendar.timeInMillis),
                sugar.toFloat(),
                insulin.toFloat(),
                note
            )
            insert(record)
        }
    }

    private suspend fun insert(record: RecordingEntity) {
        withContext(Dispatchers.IO) {
            dao.insert(record)
        }
    }
}
