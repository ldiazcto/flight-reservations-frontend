package com.tdl.flights.flightsapp.models.request

import com.tdl.flights.flightsapp.models.response.CustomerDTO

data class UpdateReservationDTO(
    val flightDate: String?,
    val passengersQuantity: String?,
    val totalPrice: String?,
    val customer: CustomerDTO?
)
