package com.tdl.flights.flightsapp.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.tdl.flights.R
import com.tdl.flights.flightsapp.listeners.OnCheckedChangeListener

class ShowReservationActivity : AppCompatActivity() {
    private lateinit var onCheckedChangeListener: OnCheckedChangeListener
    private var reservationId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_reservation)

        //val reservationJson = intent.extras?.getString("EXTRA_CREATED_RESERVATION").orEmpty()

        reservationId = intent.getStringExtra("EXTRA_CREATED_RESERVATION") ?: ""

        val reservationIdTextView: TextView = findViewById(R.id.reservationIdTextView)
        reservationIdTextView.text = "Reservation ID: $reservationId"

    }
}