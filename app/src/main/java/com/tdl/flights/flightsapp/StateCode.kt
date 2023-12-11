package com.tdl.flights.flightsapp

import com.fasterxml.jackson.annotation.JsonValue

enum class StateCode(
    override val type: String
) : EnumUtil {
    AR_C("01"),
    AR_B("02"),
    AR_K("03"),
    AR_X("04"),
    AR_W("05"),
    AR_H("06"),
    AR_U("07"),
    AR_E("08"),
    AR_P("09"),
    AR_Y("10"),
    AR_L("11"),
    AR_F("12"),
    AR_M("13"),
    AR_N("14"),
    AR_Q("15"),
    AR_R("16"),
    AR_A("17"),
    AR_J("18"),
    AR_D("19"),
    AR_Z("20"),
    AR_S("21"),
    AR_G("22"),
    AR_T("23"),
    AR_V("40");

    @JsonValue
    override fun getEnumName(): String {
        return name.replace("_", "-").uppercase()
    }

    companion object : EnumCompanion<StateCode>
}
