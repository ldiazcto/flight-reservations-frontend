package com.tdl.flights.flightsapp

import com.fasterxml.jackson.annotation.JsonFormat
import com.tdl.flights.flightsapp.Constants.ARGENTINE_TIME_ZONE
import com.tdl.flights.flightsapp.Constants.DATETIME_RFC3339_PATTERN
import java.math.BigDecimal
import java.time.ZonedDateTime

data class FlightDTO(
    val id: String,
    val airline: String,
    val type: String,
    val originAirport: String,
    val originCity: String,
    val originState: String,
    val originCountry: String,
    val destinationAirport: String,
    val destinationCity: String,
    val destinationState: String,
    val destinationCountry: String,
    val plannedDepartureTime: String,
    val realDepartureTime: String,
    val plannedArrivalTime: String,
    val realArrivalTime: String,
    val price: String
)
