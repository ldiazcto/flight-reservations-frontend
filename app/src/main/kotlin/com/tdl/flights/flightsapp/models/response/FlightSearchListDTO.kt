package com.tdl.flights.flightsapp.models.response

data class FlightSearchListDTO(
    val flights: List<FlightSearchDTO>
) {
    data class FlightSearchDTO(
        val id: String,
        val airline: String,
        val originAirport: String,
        val destinationAirport: String,
        val plannedDepartureTime: String,
        val plannedArrivalTime: String,
        val price: String
    )
}
