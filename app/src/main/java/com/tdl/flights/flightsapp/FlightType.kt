package com.tdl.flights.flightsapp

enum class FlightType(
    override val type: String
) : EnumUtil {
    NATIONAL("N"),
    INTERNATIONAL("I");

    companion object : EnumCompanion<FlightType>
}
