package com.tdl.flights.flightsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tdl.flights.R
import com.tdl.flights.flightsapp.models.response.FlightSearchListDTO

class FlightAdapter(
    private val flightSearchList: FlightSearchListDTO
) : RecyclerView.Adapter<FlightHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_flight_item, parent, false)

        return FlightHolder(view)
    }

    override fun onBindViewHolder(holder: FlightHolder, position: Int) {
        val flight = flightSearchList.flights[position]

        holder.airline.text = flight.airline
        holder.origin.text = flight.originAirport
        holder.departureTime.text = flight.plannedDepartureTime
        holder.destination.text = flight.destinationAirport
        holder.arrivalTime.text = flight.plannedArrivalTime
        holder.price.text = flight.price
    }

    override fun getItemCount(): Int {
        return flightSearchList.flights.size
    }
}