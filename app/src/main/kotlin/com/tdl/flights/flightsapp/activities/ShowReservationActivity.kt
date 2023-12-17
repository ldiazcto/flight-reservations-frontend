package com.tdl.flights.flightsapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.google.gson.Gson
import com.tdl.flights.R
import com.tdl.flights.flightsapp.models.response.ReservationDTO

class ShowReservationActivity : AppCompatActivity() {
    private lateinit var tvReservationId: AppCompatTextView
    private lateinit var createdReservation: ReservationDTO
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_reservation)

        tvReservationId = findViewById(R.id.reservationIdTextView)

        createdReservation = gson.fromJson(
            intent.extras?.getString("EXTRA_CREATED_RESERVATION").orEmpty(),
            ReservationDTO::class.java
        )

        tvReservationId.text = "Su código de reserva es: ${createdReservation.id}"
    }
}
