package com.tdl.flights.flightsapp.models.enums

import com.tdl.flights.flightsapp.utils.EnumCompanion
import com.tdl.flights.flightsapp.utils.EnumUtil

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
