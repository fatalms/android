package com.zlogene.diabeticdiray.ui.screen.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.zlogene.diabeticdiray.database.RecordingDAO
import com.zlogene.diabeticdiray.model.RecordingEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import kotlin.random.Random

class ListViewModel(
    val dao: RecordingDAO,
    application: Application
) : AndroidViewModel(application) {

    val records = dao.getAllDesc()

    fun deleteAll() {
        viewModelScope.launch {
            clear()
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            dao.clear()
        }
    }

    fun addRecord(record: RecordingEntity) {
        viewModelScope.launch {
            insert(record)
        }
    }

    private suspend fun insert(record: RecordingEntity) {
        withContext(Dispatchers.IO) {
            dao.insert(record)
        }
    }

    fun addTestData() {
        val list = listOf<RecordingEntity>(
            // last week
            RecordingEntity(
                0L,
                getDate(2021, 3, 27, 4),
                (1..10).random().toFloat(),
                (1..10).random().toFloat(),
                "All " + arrayOf("Good", "Bad", "Normal")[Random.nextInt(3)]
            ),
            RecordingEntity(
                0L,
                getDate(2021, 3, 28, 5),
                (1..10).random().toFloat(),
                (1..10).random().toFloat(),
                "All " + arrayOf("Good", "Bad", "Normal")[Random.nextInt(3)]
            ),
            RecordingEntity(
                0L,
                getDate(2021, 3, 29, 13),
                (1..10).random().toFloat(),
                (1..10).random().toFloat(),
                "All " + arrayOf("Good", "Bad", "Normal")[Random.nextInt(3)]
            ),
            RecordingEntity(
                0L,
                getDate(2021, 3, 29, 23),
                (1..10).random().toFloat(),
                (1..10).random().toFloat(),
                "All " + arrayOf("Good", "Bad", "Normal")[Random.nextInt(3)]
            ),
            // last month
            RecordingEntity(
                0L,
                getDate(2021, 3, 15, 21),
                (1..10).random().toFloat(),
                (1..10).random().toFloat(),
                "All " + arrayOf("Good", "Bad", "Normal")[Random.nextInt(3)]
            ),
            RecordingEntity(
                0L,
                getDate(2021, 3, 7, 19),
                (1..10).random().toFloat(),
                (1..10).random().toFloat(),
                "All " + arrayOf("Good", "Bad", "Normal")[Random.nextInt(3)]
            ),
            RecordingEntity(
                0L,
                getDate(2021, 3, 18, 17),
                (1..10).random().toFloat(),
                (1..10).random().toFloat(),
                "All " + arrayOf("Good", "Bad", "Normal")[Random.nextInt(3)]
            ),
            // last quarter
            RecordingEntity(
                0L,
                getDate(2021, 2, 7, 11),
                (1..10).random().toFloat(),
                (1..10).random().toFloat(),
                "All " + arrayOf("Good", "Bad", "Normal")[Random.nextInt(3)]
            ),
            RecordingEntity(
                0L,
                getDate(2021, 1, 22, 15),
                (1..10).random().toFloat(),
                (1..10).random().toFloat(),
                "All " + arrayOf("Good", "Bad", "Normal")[Random.nextInt(3)]
            ),
            RecordingEntity(
                0L,
                getDate(2021, 2, 15, 13),
                (1..10).random().toFloat(),
                (1..10).random().toFloat(),
                "All " + arrayOf("Good", "Bad", "Normal")[Random.nextInt(3)]
            ),
        )

        val iterator = list.listIterator()
        for (item in iterator) {
            addRecord(item)
        }
    }

    private fun getDate(year: Int, month: Int, day: Int, hour: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        return calendar.timeInMillis / 1000
    }
}
