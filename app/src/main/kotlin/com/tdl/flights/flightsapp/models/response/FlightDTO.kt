package com.tdl.flights.flightsapp.models.response

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
