package com.zlogene.diabeticdiray.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zlogene.diabeticdiray.model.RecordingEntity

@Database(
    entities = [RecordingEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RecordingDatabase : RoomDatabase() {
    abstract fun getRecordingDatabaseDao(): RecordingDAO

    companion object {
        @Volatile
        private var INSTANCE: RecordingDatabase? = null

        fun getInstance(context: Context): RecordingDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RecordingDatabase::class.java, "recording_table"
                    )
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
