package com.zlogene.diabeticdiray.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar

/**
 * Return date in specified format.
 * @param milliSeconds Date in milliseconds
 * @param dateFormat Date format
 * @return String representing date in specified format
 */
class CustomDataConverter {
    @SuppressLint("SimpleDateFormat")
    companion object {
        fun getDate(milliSeconds: Long, dateFormat: String): String {
            // Create a DateFormatter object for displaying date in specified format.
            val formatter = SimpleDateFormat(dateFormat)

            // Create a calendar object that will convert the date and time value in milliseconds to date.
            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = milliSeconds * 1000
            return formatter.format(calendar.time)
        }
        fun getDateUnix(milliSeconds: Long): Long {
            return (milliSeconds / 1000).toLong()
        }
    }
}
