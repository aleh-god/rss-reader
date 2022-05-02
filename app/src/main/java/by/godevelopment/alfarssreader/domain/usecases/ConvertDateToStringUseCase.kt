package by.godevelopment.alfarssreader.domain.usecases

import by.godevelopment.alfarssreader.commons.ITEM_DATE_FORMAT_PATTERN
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ConvertDateToStringUseCase @Inject constructor() {
    operator fun invoke(milliseconds: Long): String =
        SimpleDateFormat(ITEM_DATE_FORMAT_PATTERN, Locale.getDefault())
            .format(Date(milliseconds))
}