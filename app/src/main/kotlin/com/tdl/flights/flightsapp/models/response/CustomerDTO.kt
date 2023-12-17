package com.tdl.flights.flightsapp.models.response

data class CustomerDTO(
    val firstName: String,
    val lastName: String,
    val documents: List<DocumentDTO>,
    val email: String,
    val phone: PhoneDTO
)
