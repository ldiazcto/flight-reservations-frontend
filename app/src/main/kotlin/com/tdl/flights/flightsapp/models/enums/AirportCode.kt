package com.tdl.flights.flightsapp.models.enums

import com.tdl.flights.flightsapp.utils.EnumCompanion
import com.tdl.flights.flightsapp.utils.EnumUtil

enum class AirportCode(
    override val type: String
) : EnumUtil {
    BUENOS_AIRES_AEROPARQUE("AEP"),
    BUENOS_AIRES_EZEIZA("EZE"),
    BARILOCHE("BRC"),
    CATAMARCA("CTC"),
    COMODORO_RIVADAVIA("CRD"),
    CORDOBA("COR"),
    EL_PALOMAR("EPA"),
    ESQUEL("EQS"),
    FORMOSA("FMA"),
    GENERAL_PICO("GPO"),
    IGUAZU("IGR"),
    JUJUY("JUJ"),
    LA_RIOJA("IRJ"),
    MALARGUE("LGS"),
    MAR_DEL_PLATA("MDQ"),
    MENDOZA("MDZ"),
    PARANA("PRA"),
    POSADAS("PSS"),
    PUERTO_MADRYN("PMY"),
    RECONQUISTA("RCQ"),
    RESISTENCIA("RES"),
    RIO_CUARTO("RCU"),
    RIO_GALLEGOS("RGL"),
    RIO_GRANDE("RGA"),
    RIO_HONDO("RHD"),
    SALTA("SLA"),
    SAN_FERNANDO("FDO"),
    SAN_JUAN("UAQ"),
    SAN_LUIS("LUQ"),
    SAN_RAFAEL("AFA"),
    SANTA_ROSA("RSA"),
    SANTIAGO_DEL_ESTERO("SDE"),
    TUCUMAN("TUC"),
    VIEDMA("VDM"),
    VILLA_MERCEDES("VME");

    companion object : EnumCompanion<AirportCode>
}
