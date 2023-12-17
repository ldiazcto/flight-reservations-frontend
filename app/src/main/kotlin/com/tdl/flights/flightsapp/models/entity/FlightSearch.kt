package com.tdl.flights.flightsapp.models.entity

import com.tdl.flights.flightsapp.models.enums.AirlineCode
import com.tdl.flights.flightsapp.models.enums.AirportCode
import com.tdl.flights.flightsapp.models.response.FlightSearchListDTO.FlightSearchDTO
import com.tdl.flights.flightsapp.utils.Constants.DATETIME_RFC3339_PATTERN
import com.tdl.flights.flightsapp.utils.fromValue
import com.tdl.flights.flightsapp.utils.toDateFromPatternWithHours
import java.math.BigDecimal
import java.time.ZonedDateTime

data class FlightSearch(
    val id: String,
    val airline: AirlineCode,
    val originAirport: AirportCode,
    val destinationAirport: AirportCode,
    val plannedDepartureTime: ZonedDateTime,
    val plannedArrivalTime: ZonedDateTime,
    val price: BigDecimal
) {
    constructor(entity: FlightSearchDTO) : this(
        id = entity.id,
        airline = AirlineCode.fromValue(entity.airline)!!,
        originAirport = AirportCode.fromValue(entity.originAirport)!!,
        destinationAirport = AirportCode.fromValue(entity.destinationAirport)!!,
        plannedDepartureTime = entity.plannedDepartureTime.toDateFromPatternWithHours(DATETIME_RFC3339_PATTERN)!!,
        plannedArrivalTime = entity.plannedArrivalTime.toDateFromPatternWithHours(DATETIME_RFC3339_PATTERN)!!,
        price = entity.price.toBigDecimal()
    )
}
