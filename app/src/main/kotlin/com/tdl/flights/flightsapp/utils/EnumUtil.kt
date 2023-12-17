package com.tdl.flights.flightsapp.utils

interface EnumCompanion<T : Enum<T>>

interface EnumUtil {
    val type: String
    val name: String
}

inline fun <reified T> EnumCompanion<T>.fromCode(value: String): T? where T : Enum<T>, T : EnumUtil {
    return enumValues<T>().firstOrNull { it.type == value.uppercase() }
}

inline fun <reified T> EnumCompanion<T>.fromValue(value: String): T? where T : Enum<T>, T : EnumUtil {
    return enumValues<T>().firstOrNull { it.name == value.uppercase() }
}

inline fun <reified T> EnumCompanion<T>.fromPartialValue(value: String): T? where T : Enum<T>, T : EnumUtil {
    return enumValues<T>().firstOrNull { it.name.contains(value.uppercase()) }
}
