package com.tdl.flights.flightsapp.models.response

data class ReservationDTO(
    val id: String,
    val flightDate: String,
    val airline: String,
    val origin: String,
    val destination: String,
    val passengersQuantity: String,
    val totalPrice: String,
    val customer: CustomerDTO
)
