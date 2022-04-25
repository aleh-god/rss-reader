package by.godevelopment.alfarssreader.data.datautils

import android.annotation.SuppressLint
import android.util.Log
import by.godevelopment.alfarssreader.commons.DATE_FORMAT_PATTERN
import by.godevelopment.alfarssreader.commons.TAG
import by.godevelopment.alfarssreader.commons.TIME_ZONE
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ConvertFromISO8601UTC @Inject constructor() {
    @SuppressLint("SimpleDateFormat")
    operator fun invoke(dateStr: String?): Long? {
        val tz = TimeZone.getTimeZone(TIME_ZONE)
        val df: DateFormat = SimpleDateFormat(DATE_FORMAT_PATTERN)
        df.timeZone = tz
        return try {
            val resultDate = dateStr?.let { df.parse(it) }
            resultDate?.time
        } catch (e: ParseException) {
            Log.i(TAG, "ConvertFromISO8601UTC catch: ${e.message}")
            null
        }
    }
}