package com.zlogene.diabeticdiray.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.zlogene.diabeticdiray.model.RecordingEntity

@Dao
interface RecordingDAO {
    @Insert
    fun insert(recording: RecordingEntity)

    @Update
    fun update(recording: RecordingEntity)

    @Delete
    fun delete(recording: RecordingEntity)

    @Query("DELETE FROM recording_table")
    fun clear()

    @Query("SELECT * FROM recording_table ORDER BY date")
    fun getAll(): LiveData<MutableList<RecordingEntity>>

    @Query("SELECT * FROM recording_table ORDER BY date DESC")
    fun getAllDesc(): LiveData<MutableList<RecordingEntity>>

    @Query("SELECT * FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-7 days') ORDER BY sugar")
    fun getWeekSugar(): LiveData<MutableList<RecordingEntity>>

    @Query("SELECT AVG(sugar) FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-7 days')")
    fun getAVGWeekSugar(): LiveData<Float>

    @Query("SELECT * FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-1 month') ORDER BY sugar")
    fun getMonthSugar(): LiveData<MutableList<RecordingEntity>>

    @Query("SELECT AVG(sugar) FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-1 month')")
    fun getAVGMonthSugar(): LiveData<Float>

    @Query("SELECT * FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-3 month') ORDER BY sugar")
    fun getQuarterSugar(): LiveData<MutableList<RecordingEntity>>

    @Query("SELECT AVG(sugar) FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-3 month')")
    fun getAVGQuarterSugar(): LiveData<Float>

    @Query("SELECT * FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-1 year') ORDER BY sugar")
    fun getYearSugar(): LiveData<MutableList<RecordingEntity>>

    @Query("SELECT AVG(sugar) FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-1 year')")
    fun getAVGYearSugar(): LiveData<Float>

    @Query("SELECT * FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-7 days') ORDER BY insulin")
    fun getWeekInsulin(): LiveData<MutableList<RecordingEntity>>

    @Query("SELECT AVG(insulin) FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-7 days')")
    fun getAVGWeekInsulin(): LiveData<Float>

    @Query("SELECT * FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-1 month') ORDER BY insulin")
    fun getMonthInsulin(): LiveData<MutableList<RecordingEntity>>

    @Query("SELECT AVG(insulin) FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-1 month')")
    fun getAVGMonthInsulin(): LiveData<Float>

    @Query("SELECT * FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-3 month') ORDER BY insulin")
    fun getQuarterInsulin(): LiveData<MutableList<RecordingEntity>>

    @Query("SELECT AVG(insulin) FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-3 month')")
    fun getAVGQuarterInsulin(): LiveData<Float>

    @Query("SELECT * FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-1 year') ORDER BY insulin")
    fun getYearInsulin(): LiveData<MutableList<RecordingEntity>>

    @Query("SELECT AVG(insulin) FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-1 year')")
    fun getAVGYearInsulin(): LiveData<Float>
}

