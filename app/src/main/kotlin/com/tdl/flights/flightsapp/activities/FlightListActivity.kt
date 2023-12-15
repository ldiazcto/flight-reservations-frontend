package com.tdl.flights.flightsapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tdl.flights.R
import com.tdl.flights.flightsapp.adapters.FlightAdapter
import com.tdl.flights.flightsapp.api.RetrofitClient
import com.tdl.flights.flightsapp.listeners.ItemClickListener
import com.tdl.flights.flightsapp.models.response.FlightSearchListDTO
import com.tdl.flights.flightsapp.utils.Constants
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat


class FlightListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemClickListener: ItemClickListener
    private lateinit var flightAdapter: FlightAdapter
    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_list)

        // tvTitle.text = "Vuelos desde $origin hacia $destination"

        recyclerView = findViewById(R.id.flightList_recyclerView)
        floatingActionButton = findViewById(R.id.flightList_fab)

        itemClickListener = object : ItemClickListener {
            override fun onClick(s: String) {
                recyclerView.post { flightAdapter.notifyDataSetChanged() }
                Toast.makeText(
                    applicationContext,
                    "Selected : $s",
                    Toast.LENGTH_SHORT
                )
                .show()
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        floatingActionButton.setOnClickListener { _ ->
            val intent = Intent(this, ProcessReservationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadFlights() {
        val origin = intent.extras?.getString("EXTRA_ORIGIN").orEmpty()
        val destination = intent.extras?.getString("EXTRA_DESTINATION").orEmpty()
        val from = intent.extras?.getString("EXTRA_FROM").orEmpty()
        val to = intent.extras?.getString("EXTRA_TO").orEmpty()
        var flightSelected: FlightSearchListDTO
        val flightReservationsApi = RetrofitClient.flightReservationsClient
        val call = flightReservationsApi.getFlights(
            origin = origin,
            destination = destination,
            from = from,
            to = to
        )

        call.enqueue(object : retrofit2.Callback<FlightSearchListDTO> {
            override fun onResponse(call: Call<FlightSearchListDTO>, response: Response<FlightSearchListDTO>) {
                flightSelected = response.body()!!
                populateListView(response.body()!!)
            }

            override fun onFailure(call: Call<FlightSearchListDTO>, t: Throwable) {
                Toast.makeText(this@FlightListActivity, "Error al cargar los vuelos", Toast.LENGTH_SHORT).show()
            }
        })

        var btnFlightSelected: AppCompatButton

        btnFlightSelected = findViewById(R.id.btnFlightSelected)

        btnFlightSelected.setOnClickListener {
            val flightSelectedToSend = btnFlightSelected.text.toString()
            if (flightSelectedToSend.isNotEmpty()) {
                val intent = Intent(this, ProcessReservationActivity::class.java)

                intent.putExtra("FLIGHT_SELECTED", flightSelectedToSend)
                startActivity(intent)
            }
        }
    }

    private fun populateListView(flights: FlightSearchListDTO) {
        flightAdapter = FlightAdapter(flights, itemClickListener)

        recyclerView.adapter = flightAdapter
    }

    override fun onResume() {
        super.onResume()
        loadFlights()
    }
}
