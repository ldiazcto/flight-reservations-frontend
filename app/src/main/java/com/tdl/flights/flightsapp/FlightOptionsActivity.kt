package com.tdl.flights.flightsapp

import FlightList
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.widget.LinearLayout
import android.widget.TextView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
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
            Log.e("FlightOptionsActivity", "Error trying to read flights.json", e)
            return
        }

        val flightListType = object : TypeToken<FlightList>() {}.type
        val flightList: FlightList = Gson().fromJson(json, flightListType)

        val filteredFlights = flightList.flights.filter { flight ->
            flight.origin == origen && flight.destination == destino
        }
        val radioGroup = RadioGroup(this)

        for (flight in filteredFlights) {
            val radioButton = RadioButton(this)
            if (Build.VERSION.SDK_INT < 23) {
                radioButton.setTextAppearance(this, R.style.FlightOptionTextStyle)
            } else {
                radioButton.setTextAppearance(R.style.FlightOptionTextStyle)
            }

            val flightInfoText = "<b>Vuelo ${flight.id}:</b><br/>" +
                    "<small>Salida ${flight.departureTime}, Llegada ${flight.arrivalTime}, Precio ${flight.price}</small>"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                radioButton.text = Html.fromHtml(flightInfoText, Html.FROM_HTML_MODE_COMPACT)
            } else {
                @Suppress("DEPRECATION")
                radioButton.text = Html.fromHtml(flightInfoText)
            }
            radioButton.id = View.generateViewId()

            radioButton.maxLines = 4  // Número máximo de líneas a mostrar
            radioButton.ellipsize = TextUtils.TruncateAt.END
            radioGroup.addView(radioButton)
        }
        llFlightOptions.addView(radioGroup)

        val btnContinue = findViewById<Button>(R.id.btnContinue)
        btnContinue.setOnClickListener {
            // obtengo el ID del RadioButton seleccionado
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId

            if (selectedRadioButtonId == -1) {
                Toast.makeText(this, "Selecciona un vuelo antes de continuar", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ProcessReservationActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
