package com.tdl.flights.flightsapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.tdl.flights.R
import com.tdl.flights.flightsapp.fragments.DatePickerFragment
import com.tdl.flights.flightsapp.utils.Constants.DATE_CALENDAR_OUTPUT_PATTERN
import com.tdl.flights.flightsapp.utils.Constants.DATE_PATTERN
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class SearchFlightsActivity : AppCompatActivity() {
    private lateinit var etOrigin: AppCompatEditText
    private lateinit var etDestination: AppCompatEditText
    private lateinit var etFromDate: AppCompatEditText
    private lateinit var btnSearchFlights: AppCompatButton
    private lateinit var date: Date

    private var isDatePickerSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_flights)

        etOrigin = findViewById(R.id.etOrigin)
        etDestination = findViewById(R.id.etDestination)
        etFromDate = findViewById(R.id.etFromDate)
        btnSearchFlights = findViewById(R.id.btnSearchFlights)

        etFromDate.setOnClickListener { showDatePickerDialog() }

        btnSearchFlights.setOnClickListener{
            val dateFormat = SimpleDateFormat(DATE_PATTERN)

            val origin = etOrigin.text.toString()
            val destination = etDestination.text.toString()
            var from = dateFormat.format(date)

            if (isDatePickerSelected && origin.isNotEmpty() && destination.isNotEmpty()) {
                val intent = Intent(this, FlightListActivity::class.java)

                intent.putExtra("EXTRA_ORIGIN", origin)
                intent.putExtra("EXTRA_DESTINATION", destination)
                intent.putExtra("EXTRA_FROM", from)
                intent.putExtra("EXTRA_TO", from)

                startActivity(intent)
            }
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "dataPicker")
    }

    @SuppressLint("SimpleDateFormat")
    fun onDateSelected(day: Int, month: Int, year: Int) {
        val calendar = Calendar.getInstance().apply {
            set(year, month, day)
        }

        date = calendar.time

        val dateFormat = SimpleDateFormat(DATE_CALENDAR_OUTPUT_PATTERN)
        val formattedDate = dateFormat.format(date)
            .replace(".", String())
            .split(" ")
            .map { word ->
                word.lowercase().replaceFirstChar { it.uppercase() }
            }
            .joinToString(" ")

        etFromDate.setText(formattedDate)
        isDatePickerSelected = true
    }
}
