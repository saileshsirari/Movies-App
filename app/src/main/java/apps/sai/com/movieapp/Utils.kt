package apps.sai.com.movieapp

import java.text.SimpleDateFormat
import java.util.*
object Utils {
    const val YYYY_MM_DD_DASH = "yyyy-MM-dd"
    const val DD_MMM_YYYY = "dd/MMM/yyyy"

    fun String.toDate(
        pattern: String,
        locale: Locale = Locale.ENGLISH,
        timeZone: TimeZone = TimeZone.getDefault()
    ): Date? {
        return try {
            SimpleDateFormat(pattern, locale).apply { this.timeZone = timeZone }.parse(this)
        } catch (e: Exception) {
            null
        }
    }

    fun Any.formatDate(
        pattern: String,
        locale: Locale = Locale.ENGLISH,
        timeZone: TimeZone = TimeZone.getDefault()
    ): String? {
        return try {
            SimpleDateFormat(pattern, locale).apply { this.timeZone = timeZone }.format(this)
        } catch (e: Exception) {
            null
        }
    }

    fun String?.safe(): String {
        if (this == null)
            return ""

        return this
    }
    fun getFormatedDate(text: String?): String {
        return (text?.toDate(Utils.YYYY_MM_DD_DASH)
            ?.formatDate(Utils.DD_MMM_YYYY).safe())
    }
    fun Double.format2Places():Double{
        return String.format("%.2f",this).toDouble()
    }
}
