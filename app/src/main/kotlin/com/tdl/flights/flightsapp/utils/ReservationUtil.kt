package com.tdl.flights.flightsapp.utils

import com.tdl.flights.flightsapp.utils.Constants.ARGENTINE_TIME_ZONE
import com.tdl.flights.flightsapp.utils.Constants.ARGENTINE_ZONE
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun String.toDateFromPatternWithoutHours(pattern: String): ZonedDateTime? {
    return try {
        val timeFormatter = DateTimeFormatter.ofPattern(pattern)
            .withZone(ZoneId.of(ARGENTINE_TIME_ZONE))

        val localDate = LocalDate.parse(this, timeFormatter)

        // Set time
        val zeroTimeLocalDate = localDate.atStartOfDay()

        // Convert date to Argentina's time
        val zonedDateTime = ZonedDateTime.of(zeroTimeLocalDate, ZoneId.of(ARGENTINE_TIME_ZONE))

        // Convert date to UTC
        zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"))
    } catch (e: Exception) {
        null
    }
}

fun ZonedDateTime.formatToString(pattern: String): String {
    return DateTimeFormatter.ofPattern(pattern).format(this.withZoneSameInstant(ZoneId.of(ARGENTINE_ZONE)))
}
