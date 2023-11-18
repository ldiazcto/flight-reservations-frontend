package com.tdl.flights.flightsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.tdl.flights.R

class FlightOptionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_options)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)

        //get extras, ?--> nullable
        val origen: String= intent.extras?.getString("EXTRA_ORIGEN").orEmpty()//le aviso que es un string
        val destino: String= intent.extras?.getString("EXTRA_DESTINO").orEmpty()
        tvTitle.text = "Vuelos desde $origen hacia $destino"

    }
}