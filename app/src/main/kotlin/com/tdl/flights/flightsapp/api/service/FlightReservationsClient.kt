package com.tdl.flights.flightsapp.api.service

import com.tdl.flights.flightsapp.models.response.FlightDTO
import com.tdl.flights.flightsapp.models.response.ReservationDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FlightReservationsClient {
    @GET("/api/flights?airline=all&origin=buenos_aires_aeroparque&destination=salta&from=2023-12-30&to=2023-12-30")
    fun getFlights(): Call<List<FlightDTO>>

    @GET("/api/reservations/{id}")
    fun getReservation(
        @Path("id") id: String
    ): Call<ReservationDTO>

    @GET("/api/reservations/{id}")
    fun deleteReservation(
        @Path("id") id: String
    ): Call<ReservationDTO>
}
