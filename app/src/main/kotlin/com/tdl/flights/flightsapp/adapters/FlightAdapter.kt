package com.tdl.flights.flightsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tdl.flights.R
import com.tdl.flights.flightsapp.listeners.OnCheckedChangeListener
import com.tdl.flights.flightsapp.models.entity.FlightSearch
import com.tdl.flights.flightsapp.models.enums.AirlineCode
import com.tdl.flights.flightsapp.utils.Constants.DATE_WITH_DOTS_PATTERN
import com.tdl.flights.flightsapp.utils.Constants.HOUR_WITHOUT_SECONDS
import com.tdl.flights.flightsapp.utils.formatToString
import java.time.Duration
import java.time.ZonedDateTime

class FlightAdapter(
    private val flightSearch: List<FlightSearch>,
    private val onCheckedChangeListener: OnCheckedChangeListener
) : RecyclerView.Adapter<FlightHolder>() {

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_flight_item, parent, false)

        return FlightHolder(view)
    }

    override fun onBindViewHolder(holder: FlightHolder, position: Int) {
        val flight = flightSearch[position]

        holder.airline.text = formatAirlineText(flight.airline)
        holder.origin.text = flight.originAirport.type
        holder.departureTime.text = flight.plannedDepartureTime.formatToString(HOUR_WITHOUT_SECONDS)
        holder.durationTime.text = formatDurationTimeText(flight.plannedDepartureTime, flight.plannedArrivalTime)
        holder.destination.text = flight.destinationAirport.type
        holder.arrivalTime.text = flight.plannedArrivalTime.formatToString(HOUR_WITHOUT_SECONDS)
        holder.flightDate.text = flight.plannedArrivalTime.formatToString(DATE_WITH_DOTS_PATTERN)
        holder.price.text = "$${flight.price.toString().replace(".", ",")}"

        holder.radioButton.isChecked = position == selectedPosition
        holder.radioButton.setOnCheckedChangeListener { _, b ->
            if (b) {
                selectedPosition = holder.adapterPosition
                onCheckedChangeListener.onRadioButtonChanged(selectedPosition)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return flightSearch.size
    }

    private fun formatAirlineText(airline: AirlineCode): String {
        return airline.name
            .replace("_", " ")
            .split(" ")
            .map { word ->
                word.lowercase().replaceFirstChar { it.uppercase() }
            }
            .joinToString(" ")
    }

    private fun formatDurationTimeText(
        firstTime: ZonedDateTime,
        secondTime: ZonedDateTime
    ): String {
        val durationTime = Duration.between(secondTime, firstTime).abs()
        val hoursPart = durationTime.toHours()
        val minutesPart = durationTime.toMinutes() - (hoursPart * 60)

        return "Duración: $hoursPart h $minutesPart m"
    }
}
