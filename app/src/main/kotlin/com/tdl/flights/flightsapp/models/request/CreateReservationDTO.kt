package com.tdl.flights.flightsapp.models.request

import com.tdl.flights.flightsapp.models.response.CustomerDTO

data class CreateReservationDTO(
    val flightDate: String?,
    val airline: String?,
    val origin: String?,
    val destination: String?,
    val passengersQuantity: String?,
    val totalPrice: String?,
    val customer: CustomerDTO?
)
