package com.tdl.flights.flightsapp.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tdl.flights.R

class FlightHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    var airline: TextView
    var origin: TextView
    var departureTime: TextView
    var destination: TextView
    var arrivalTime: TextView
    var price: TextView

    init {
        airline = itemView.findViewById(R.id.flightListItem_airline)
        origin = itemView.findViewById(R.id.flightListItem_origin)
        departureTime = itemView.findViewById(R.id.flightListItem_departureTime)
        destination = itemView.findViewById(R.id.flightListItem_destination)
        arrivalTime = itemView.findViewById(R.id.flightListItem_arrivalTime)
        price = itemView.findViewById(R.id.flightListItem_price)
    }
}
