package com.nasa.pic.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateExtension {

    fun getCurrentDate(calendar: Calendar): String {
        return "${calendar.get(Calendar.YEAR)}-" +
                "${calendar.get(Calendar.MONTH) + 1}-" +
                "${calendar.get(Calendar.DATE)}"
    }

    fun convertStringToDate(givenDate: String): Date {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        var date = Date()
        try {
            date = format.parse(givenDate)
        } catch (e: ParseException) {
        }
        return date
    }

    fun convertDateFormat(date: Date?): String {
        if (date == null) return ""
        val spf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return spf.format(date)
    }


}