package com.tdl.flights.flightsapp.adapters

import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tdl.flights.R

class FlightHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    var radioButton: RadioButton
    var airline: TextView
    var origin: TextView
    var departureTime: TextView
    var durationTime: TextView
    var destination: TextView
    var arrivalTime: TextView
    var flightDate: TextView
    var price: TextView

    init {
        radioButton = itemView.findViewById(R.id.flightListItem_radioButton)
        airline = itemView.findViewById(R.id.flightListItem_airline)
        origin = itemView.findViewById(R.id.flightListItem_origin)
        departureTime = itemView.findViewById(R.id.flightListItem_departureTime)
        durationTime = itemView.findViewById(R.id.flightListItem_durationTime)
        destination = itemView.findViewById(R.id.flightListItem_destination)
        arrivalTime = itemView.findViewById(R.id.flightListItem_arrivalTime)
        flightDate = itemView.findViewById(R.id.flightListItem_flightDate)
        price = itemView.findViewById(R.id.flightListItem_price)
    }
}
