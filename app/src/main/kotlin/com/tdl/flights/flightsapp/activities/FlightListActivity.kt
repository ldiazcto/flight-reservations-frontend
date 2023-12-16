package com.tdl.flights.flightsapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.tdl.flights.R
import com.tdl.flights.flightsapp.adapters.FlightAdapter
import com.tdl.flights.flightsapp.api.RetrofitClient
import com.tdl.flights.flightsapp.listeners.OnCheckedChangeListener
import com.tdl.flights.flightsapp.models.entity.FlightSearch
import com.tdl.flights.flightsapp.models.response.FlightSearchListDTO
import com.tdl.flights.flightsapp.models.response.FlightSearchListDTO.FlightSearchDTO
import retrofit2.Call
import retrofit2.Response

class FlightListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var onCheckedChangeListener: OnCheckedChangeListener
    private lateinit var flightAdapter: FlightAdapter
    private lateinit var flightSelectedButton: AppCompatButton
    private lateinit var flightSearch: FlightSearchListDTO
    private lateinit var selectedFlight: FlightSearchDTO
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_list)

        recyclerView = findViewById(R.id.flightList_recyclerView)
        flightSelectedButton = findViewById(R.id.btnFlightSelected)

        onCheckedChangeListener = object : OnCheckedChangeListener {
            override fun onRadioButtonChanged(position: Int) {
                recyclerView.post { flightAdapter.notifyDataSetChanged() }

                if (position != -1) {
                    selectedFlight = flightSearch.flights[position]
                }
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        flightSelectedButton.setOnClickListener { _ ->
            val intent = Intent(this, ProcessReservationActivity::class.java)

            intent.putExtra("EXTRA_SELECTED_FLIGHT", gson.toJson(selectedFlight))

            startActivity(intent)
        }
    }

    private fun loadFlights() {
        val origin = intent.extras?.getString("EXTRA_ORIGIN").orEmpty()
        val destination = intent.extras?.getString("EXTRA_DESTINATION").orEmpty()
        val from = intent.extras?.getString("EXTRA_FROM").orEmpty()
        val to = intent.extras?.getString("EXTRA_TO").orEmpty()
        val flightReservationsApi = RetrofitClient.flightReservationsClient
        val call = flightReservationsApi.getFlights(
            origin = origin,
            destination = destination,
            from = from,
            to = to
        )

        call.enqueue(object : retrofit2.Callback<FlightSearchListDTO> {
            override fun onResponse(call: Call<FlightSearchListDTO>, response: Response<FlightSearchListDTO>) {
                flightSearch = response.body()!!
                populateListView(
                    flightSearch.flights.map { FlightSearch(it) }
                )
            }

            override fun onFailure(call: Call<FlightSearchListDTO>, t: Throwable) {
                Toast.makeText(this@FlightListActivity, "Error al cargar los vuelos", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun populateListView(flights: List<FlightSearch>) {
        flightAdapter = FlightAdapter(flights, onCheckedChangeListener)

        recyclerView.adapter = flightAdapter
    }

    override fun onResume() {
        super.onResume()
        loadFlights()
    }
}
