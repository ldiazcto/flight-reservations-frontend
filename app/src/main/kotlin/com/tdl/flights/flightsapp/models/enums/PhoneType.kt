package com.tdl.flights.flightsapp.models.enums

import com.tdl.flights.flightsapp.utils.EnumCompanion
import com.tdl.flights.flightsapp.utils.EnumUtil

enum class PhoneType(
    override val type: String
) : EnumUtil {
    CELL_PHONE("001"),
    LANDLINE_PHONE("002");

    companion object : EnumCompanion<PhoneType>
}
