package com.tdl.flights.flightsapp

import retrofit2.Call
import retrofit2.http.GET

interface MyApiService {
    @get:GET("flights?airline=all&origin=buenos_aires_aeroparque&destination=salta&from=2023-12-30&to=2023-12-30")
    val myData: Call<FlightDTO?>?
}