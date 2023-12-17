package com.tdl.flights.flightsapp.api.service

import com.tdl.flights.flightsapp.models.request.CreateReservationDTO
import com.tdl.flights.flightsapp.models.request.UpdateReservationDTO
import com.tdl.flights.flightsapp.models.response.FlightSearchListDTO
import com.tdl.flights.flightsapp.models.response.ReservationDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FlightReservationsClient {
    @GET("/api/flights")
    fun getFlights(
        @Query("airline") airline: String = "ALL",
        @Query("origin")origin: String,
        @Query("destination") destination: String,
        @Query("from") from: String,
        @Query("to") to: String,
    ): Call<FlightSearchListDTO>

    @GET("/api/reservations/{id}")
    fun getReservation(
        @Path("id") id: String
    ): Call<ReservationDTO>

    @POST("/api/reservations")
    fun createReservation(
        @Body body: CreateReservationDTO
    ): Call<ReservationDTO>

    @PATCH("/api/reservations/{id}")
    fun updateReservation(
        @Body body: UpdateReservationDTO
    ): Call<ReservationDTO>

    @DELETE("/api/reservations/{id}")
    fun deleteReservation(
        @Path("id") id: String
    ): Call<Void>
}
