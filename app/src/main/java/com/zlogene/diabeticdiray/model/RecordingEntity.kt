package com.zlogene.diabeticdiray.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
@Entity(tableName = "recording_table")
data class RecordingEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var recordingId: Long = 0L,

    @ColumnInfo(name = "date")
    var date: Long,

    @ColumnInfo(name = "sugar")
    var sugar: Float,

    @ColumnInfo(name = "insulin")
    var insulin: Float,

    @ColumnInfo(name = "textNote")
    var textNote: String
) : Parcelable
