package com.tdl.flights.flightsapp

import FlightList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.util.Log
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.tdl.flights.R
import com.google.gson.Gson
import java.io.IOException

class FlightOptionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_options)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val llFlightOptions = findViewById<LinearLayout>(R.id.llFlightOptions)

        //get extras, ?--> nullable
        val origen: String= intent.extras?.getString("EXTRA_ORIGEN").orEmpty()//le aviso que es un string
        val destino: String= intent.extras?.getString("EXTRA_DESTINO").orEmpty()
        tvTitle.text = "Vuelos desde $origen hacia $destino"

        val json: String = try {
            assets.open("flights.json").use {
                it.bufferedReader().use { reader ->
                    reader.readText()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("FlightOptionsActivity", "Error al leer flights.json", e)
            return
        }

        // Convertir el JSON a objetos Kotlin usando Gson
        val flightListType = object : TypeToken<FlightList>() {}.type
        val flightList: FlightList = Gson().fromJson(json, flightListType)

        // Mostrar las opciones de vuelo en tu diseño
        for (flight in flightList.flights) {
            val textView = TextView(this)
            textView.text = "Vuelo ${flight.id}: Salida ${flight.departureTime}, Llegada ${flight.arrivalTime}, Precio ${flight.price}"
            llFlightOptions.addView(textView)
        }
    }
}
