package com.tdl.flights.flightsapp.api

import com.tdl.flights.flightsapp.models.response.ReservationDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FlightReservationsApi {
    @GET("/api/reservations/{id}")
    fun getReservation(
        @Path("id") id: String
    ): Call<ReservationDTO>

    @GET("/api/reservations/{id}")
    fun deleteReservation(
        @Path("id") id: String
    ): Call<ReservationDTO>
}
