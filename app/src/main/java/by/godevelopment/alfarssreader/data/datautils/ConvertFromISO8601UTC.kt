package by.godevelopment.alfarssreader.data.datautils

import android.annotation.SuppressLint
import by.godevelopment.alfarssreader.commons.DATE_FORMAT_PATTERN
import by.godevelopment.alfarssreader.commons.TIME_ZONE
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ConvertFromISO8601UTC @Inject constructor() {
    @SuppressLint("SimpleDateFormat")
    operator fun invoke(dateStr: String?): Long? {
        return try {
            with(SimpleDateFormat(DATE_FORMAT_PATTERN)) {
                timeZone = TimeZone.getTimeZone(TIME_ZONE)
                dateStr?.let { parse(it) }.let { resultDate ->
                    resultDate?.time
                }
            }
        } catch (e: ParseException) {
            null
        }
    }
}
