package com.tdl.flights.flightsapp

import com.fasterxml.jackson.annotation.JsonFormat
import com.tdl.flights.flightsapp.Constants.ARGENTINE_TIME_ZONE
import com.tdl.flights.flightsapp.Constants.DATETIME_RFC3339_PATTERN
import java.math.BigDecimal
import java.time.ZonedDateTime

data class FlightDTO(
    val id: String,
    val airline: AirlineCode,
    val type: FlightType,
    val originAirport: AirportCode,
    val originCity: String,
    val originState: StateCode,
    val originCountry: CountryCode,
    val destinationAirport: AirportCode,
    val destinationCity: String,
    val destinationState: StateCode,
    val destinationCountry: CountryCode,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_RFC3339_PATTERN, timezone = ARGENTINE_TIME_ZONE)
    val plannedDepartureTime: ZonedDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_RFC3339_PATTERN, timezone = ARGENTINE_TIME_ZONE)
    val realDepartureTime: ZonedDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_RFC3339_PATTERN, timezone = ARGENTINE_TIME_ZONE)
    val plannedArrivalTime: ZonedDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_RFC3339_PATTERN, timezone = ARGENTINE_TIME_ZONE)
    val realArrivalTime: ZonedDateTime,
    val price: BigDecimal
)
