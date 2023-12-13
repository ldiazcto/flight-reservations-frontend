package com.tdl.flights.flightsapp
data class Flight(
    val id: Int,
    val airline: String,
    val origin: String,
    val destination: String,
    val departureTime: String,
    val arrivalTime: String,
    val price: Double,
    val flights: List<Flight>
)