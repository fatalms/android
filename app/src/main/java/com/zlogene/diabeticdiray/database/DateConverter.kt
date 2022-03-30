package com.zlogene.diabeticdiray.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class DateConverter {
    var gson = Gson()

    @TypeConverter
    fun dateToString(date: Date): String {
        return gson.toJson(date)
    }

    @TypeConverter
    fun dateFromString(date: String): Date {
        val type = object : TypeToken<Date>() {}.type
        return gson.fromJson(date, type)
    }
}
