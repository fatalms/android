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

    /**SUGAR*/
    // *********Get all value for sugar WEEK**********
    @Query("SELECT * FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-7 days') ORDER BY sugar")
    fun getWeekSugar(): LiveData<MutableList<RecordingEntity>>

    @Query("SELECT AVG(sugar) FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-7 days')")
    fun getAVGWeekSugar(): LiveData<Float>

    // *********Get all value for sugar MONTH*********
    @Query("SELECT * FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-1 month') ORDER BY sugar")
    fun getMonthSugar(): LiveData<MutableList<RecordingEntity>>

    @Query("SELECT AVG(sugar) FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-1 month')")
    fun getAVGMonthSugar(): LiveData<Float>

    // *********Get all value for sugar QUARTER********
    @Query("SELECT * FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-3 month') ORDER BY sugar")
    fun getQuarterSugar(): LiveData<MutableList<RecordingEntity>>

    @Query("SELECT AVG(sugar) FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-3 month')")
    fun getAVGQuarterSugar(): LiveData<Float>

    // *********Get all value for sugar YEAR********
    @Query("SELECT * FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-1 year') ORDER BY sugar")
    fun getYearSugar(): LiveData<MutableList<RecordingEntity>>

    @Query("SELECT AVG(sugar) FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-1 year')")
    fun getAVGYearSugar(): LiveData<Float>

    /**INSULIN*/
    // *********Get all value for insulin WEEK********
    @Query("SELECT * FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-7 days') ORDER BY insulin")
    fun getWeekInsulin(): LiveData<MutableList<RecordingEntity>>

    @Query("SELECT AVG(insulin) FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-7 days')")
    fun getAVGWeekInsulin(): LiveData<Float>

    // *********Get all value for insulin MONTH********
    @Query("SELECT * FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-1 month') ORDER BY insulin")
    fun getMonthInsulin(): LiveData<MutableList<RecordingEntity>>

    @Query("SELECT AVG(insulin) FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-1 month')")
    fun getAVGMonthInsulin(): LiveData<Float>

    // *********Get all value for insulin QUARTER********
    @Query("SELECT * FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-3 month') ORDER BY insulin")
    fun getQuarterInsulin(): LiveData<MutableList<RecordingEntity>>

    @Query("SELECT AVG(insulin) FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-3 month')")
    fun getAVGQuarterInsulin(): LiveData<Float>

    // *********Get all value for insulin YEAR********
    @Query("SELECT * FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-1 year') ORDER BY insulin")
    fun getYearInsulin(): LiveData<MutableList<RecordingEntity>>

    @Query("SELECT AVG(insulin) FROM recording_table WHERE date < strftime('%s','now') AND date > strftime('%s','now','-1 year')")
    fun getAVGYearInsulin(): LiveData<Float>
}
// SELECT * FROM recording_table WHERE strftime(date) >  strftime('%s','now')
// SELECT * FROM recording_table WHERE strftime(date) BETWEEN strftime('%s','now') AND strftime('%s','now','-7 days') ORDER BY sugar
// SELECT strftime('%s','now','-7 days') 	1619464929
// SELECT strftime('%s','now')           	1620069757
// SELECT * FROM recording_table WHERE date > strftime('%s','now')
