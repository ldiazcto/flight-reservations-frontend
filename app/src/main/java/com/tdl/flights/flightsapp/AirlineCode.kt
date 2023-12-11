package com.tdl.flights.flightsapp

enum class AirlineCode(
    override val type: String
) : EnumUtil {
    AEROLINEAS_ARGENTINAS("AR"),
    FLYBONDI("FO"),
    JETSMART("JA"),
    LATAM_AIRLINES("LA"),
    ALL("");

    companion object : EnumCompanion<AirlineCode>
}
