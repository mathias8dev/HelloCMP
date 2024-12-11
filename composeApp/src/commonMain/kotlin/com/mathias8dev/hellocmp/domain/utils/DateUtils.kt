package com.mathias8dev.hellocmp.domain.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime


@OptIn(FormatStringsInDatetimeFormats::class)
fun String.parseLocalTime(
    format: TimeFormat,
): LocalTime = LocalTime.parse(this, LocalTime.Format { byUnicodePattern(format.format) })

fun String.parseLocalTimeOrNull(
    format: TimeFormat,
): LocalTime? = kotlin.runCatching {
    this.parseLocalTime(format)
}.getOrNull()

@OptIn(FormatStringsInDatetimeFormats::class)
fun String.parseLocalDate(
    format: DateFormat,
): LocalDate {
    return LocalDate.parse(this, LocalDate.Format { byUnicodePattern(format.format) })
}

fun String.parseLocalDateOrNull(
    format: DateFormat,
): LocalDate? = kotlin.runCatching {
    this.parseLocalDate(format)
}.getOrNull()

@OptIn(FormatStringsInDatetimeFormats::class)
fun String.parseLocalDateTime(
    format: DateTimeFormat,
): LocalDateTime =
    LocalDateTime.parse(this, LocalDateTime.Format { byUnicodePattern(format.format) })

fun String.parseLocalDateTimeOrNull(
    format: DateTimeFormat,
): LocalDateTime? {
    return kotlin.runCatching {
        this.parseLocalDateTime(format)
    }.getOrNull()
}


fun LocalDate.toEpochMilliseconds(): Long {
    return this.atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds()
}

fun LocalDateTime.toEpochMilliseconds(): Long {
    return this.toInstant(TimeZone.UTC).toEpochMilliseconds()
}

fun LocalDateTime.toLocalDate(): LocalDate {
    return this.toInstant(TimeZone.UTC).toLocalDateTime(TimeZone.UTC).date
}

fun Long.millisToLocalDate(): LocalDate {
    return toDefaultLocalDateTime().date
}

fun Long.toDefaultLocalDateTime(): LocalDateTime {
    return Instant.fromEpochMilliseconds(this).toLocalDateTime(TimeZone.UTC)
}

fun Long.millisToDefaultLocalDate(): LocalDate {
    return Instant.fromEpochMilliseconds(this).toLocalDateTime(TimeZone.UTC).toLocalDate()
}


@OptIn(FormatStringsInDatetimeFormats::class)
fun LocalDate.print(
    format: DateFormat,
): String = this.format(LocalDate.Format { byUnicodePattern(format.format) })

@OptIn(FormatStringsInDatetimeFormats::class)
fun LocalDateTime.print(
    format: DateTimePrintFormat,
): String = this.format(LocalDateTime.Format { byUnicodePattern(format.format) })


interface DateTimePrintFormat {
    val format: String
}

enum class DateTimeFormat(override val format: String) : DateTimePrintFormat {
    FMT_DATE_TIME("yyyy-MM-dd'T'HH:mm:ss"),
    FMT_DATE_TIME_ZONED("yyyy-MM-dd'T'HH:mm:ss'Z'"),
    FMT_DATE_TIME_MILLIS_TIME_ZONED("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"),
    FMT_DATE_TIME_MILLIS("yyyy-MM-dd'T'HH:mm:ss.SSS"),
    FMT_DATE_TIME_MILLIS_2("yyyy-MM-dd'T'HH:mm:ss.S"),
    FMT_DATE_TIME_X("yyyy-MM-dd'T'HH:mm:ssX"),
    FMT_DATE_TIME_ISO_CACHING("EEE, dd MMM yyyy HH:mm:ss z"),
    FMT_DATE_TIME_PRETTY("EEEE dd MMMM HH'H'mm"),
    FMT_DATE_TIME_PRETTY_NO_DAY("dd MMM YYYY, HH'h'mm"),
    FMT_DATE_TIME_MAINTENANCE("dd/MM/yyyy HH:mm"),
    FMT_DATE_TIME_ACCORDS("yyyy-MM-dd HH:mm:ss"),
    FMT_DATE_TIME_ERL("yyyy-MM-dd'T'HH:mm:ssXXX"),
    FMT_DATE_TIME_DMYHM("dd-MM-yyyy HH:mm:ss"),
    FMT_DATE_TIME_T("yyyy-MM-dd'T'HH:mm:ss"),
    FMT_DATE_TIME_PRETTY_SS("dd MMM YYYY, HH':'mm")
}

enum class DateFormat(override val format: String) :
    DateTimePrintFormat {
    FMT_DATE("yyyy-MM-dd"),
    FMT_DATE_PRETTY("EEEE dd MMMM"),
    FMT_DATE_PRETTY_DAY_MONTH("EEEE d MMMM"),
    FMT_DATE_DAY_MONTH("d MMM"),
    FMT_DATE_DAY_FULL_MONTH("d MMMM"),
    FMT_DATE_DAY_MONTH_YEAR("dd MMMM yyyy"),
    FMT_DATE_FULL_DAY_MONTH_YEAR("EEEE dd MMMM yyyy"),
    FMT_DATE_DAY_MONTH_YEAR_NO_ZERO("d MMMM yyyy"),
    FMT_DATE_DAY_MONTH_SHORT_YEAR("dd MMM yyyy"),
    FMT_DATE_DAY_MONTH_YEAR_SHORT("d MMM yyyy"),
    FMT_DATE_DAY_MONTH_YEAR_SHORT_LINE_BREAK("d MMM\nyyyy"),
    FMT_DATE_MONTH("MMMM yyyy"),
    FMT_DATE_MONTH_SHORT("MMM yyyy"),
    FMT_DATE_MONTH_WITHOUT_YEAR("MMMM"),
    FMT_DATE_YEAR("yyyy"),
    FMT_DATE_WITH_SLASH("dd/MM/yyyy"),
    FMT_DAY_MONTH("dd MMM"),
    FMT_DAY_FULL_MONTH("dd MMMM"),
    FMT_MONTH("MMM"),
    FMT_DAY_FIRST_LETTER("EEEEE"),
    FMT_DAY_NAME_NUMBER("EEEE dd"),
    FMT_DAY_NAME_NUMBER2("EEEE d"),
    FMT_DAY_NAME_MONTH_NUMBER("EEEE dd/MM"),
    FMT_DAY_NAME("EEEE"),
    FMT_DAY_NAME_THREE("EEE"),
    FMT_DATE_WITHOUT_DAY("yyyy-MM"),
    FMT_EXPIRY_DATE("MM/yy"),
    FMT_ATOS_WALLET("E MMM dd HH:mm:ss ZZZZ y"),
    FMT_ATOS_EXPIRY_DATE("YYYYMM"),
    FMT_DATE_COMPLETE("EEEE d MMMM yyyy"),
    FMT_DATE_SHORT_DAY_MONTH("EEE. d MMM yyyy"),
}

enum class TimeFormat(override val format: String) :
    DateTimePrintFormat {
    FMT_TIME("HH:mm:ss"),
    FMT_HOURS_MIN_PRETTY("H'h'mm"),
    FMT_HOURS_PRETTY("HH'h'mm"),
    FMT_HOURS_MIN("HH:mm"),
    FMT_HOURS("H'h'"),
    FMT_HOURS_WITH_ZERO("HH"),
}