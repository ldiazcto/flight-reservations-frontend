package com.tdl.flights.flightsapp.activities

import android.content.Intent
import android.icu.math.BigDecimal.ROUND_HALF_EVEN
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.google.gson.Gson
import com.tdl.flights.R
import com.tdl.flights.flightsapp.api.RetrofitClient
import com.tdl.flights.flightsapp.models.entity.FlightSearch
import com.tdl.flights.flightsapp.models.enums.DocumentType
import com.tdl.flights.flightsapp.models.enums.PhoneType
import com.tdl.flights.flightsapp.models.request.CreateReservationDTO
import com.tdl.flights.flightsapp.models.response.CustomerDTO
import com.tdl.flights.flightsapp.models.response.DocumentDTO
import com.tdl.flights.flightsapp.models.response.FlightSearchListDTO
import com.tdl.flights.flightsapp.models.response.FlightSearchListDTO.FlightSearchDTO
import com.tdl.flights.flightsapp.models.response.PhoneDTO
import com.tdl.flights.flightsapp.models.response.ReservationDTO
import com.tdl.flights.flightsapp.utils.Constants.DATETIME_RFC3339_PATTERN
import com.tdl.flights.flightsapp.utils.formatToString
import retrofit2.Call
import retrofit2.Response
import java.math.BigInteger
import java.math.RoundingMode

class ProcessReservationActivity : AppCompatActivity() {
    private lateinit var etFirstName: AppCompatEditText
    private lateinit var etLastName: AppCompatEditText
    private lateinit var etDocumentNumber: AppCompatEditText
    private lateinit var etPhoneNumber: AppCompatEditText
    private lateinit var etEmail: AppCompatEditText
    private lateinit var btnEndReservation: AppCompatButton
    private lateinit var createdReservation: ReservationDTO
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_process_reservation)

        etFirstName = findViewById<AppCompatEditText>(R.id.etFirstName)
        etLastName = findViewById<AppCompatEditText>(R.id.etLastName)
        etDocumentNumber = findViewById<AppCompatEditText>(R.id.etDocumentNumber)
        etPhoneNumber = findViewById<AppCompatEditText>(R.id.etPhoneNumber)
        etEmail = findViewById<AppCompatEditText>(R.id.etEmail)
        btnEndReservation = findViewById<AppCompatButton>(R.id.btnEndReservation)

        val selectedFlight = FlightSearch(
            gson.fromJson(
                intent.extras?.getString("EXTRA_SELECTED_FLIGHT").orEmpty(),
                FlightSearchDTO::class.java
            )
        )

        btnEndReservation.setOnClickListener{
            val firstName = etFirstName.text.toString()
            val lastName = etLastName.text.toString()
            val documentNumber = etDocumentNumber.text.toString()
            val phoneNumber = etPhoneNumber.text.toString()
            val email = etEmail.text.toString()

            val isValid = firstName.isNotEmpty() && lastName.isNotEmpty() && documentNumber.isNotEmpty() &&
                phoneNumber.isNotEmpty() && email.isNotEmpty()

            if (isValid) {
                val passengersQuantity = BigInteger.ONE.toInt()
                val totalPrice = selectedFlight.price * passengersQuantity.toBigDecimal()

                val body = CreateReservationDTO(
                    flightDate = selectedFlight.plannedDepartureTime.formatToString(DATETIME_RFC3339_PATTERN),
                    airline = selectedFlight.airline.name,
                    origin = selectedFlight.originAirport.name,
                    destination = selectedFlight.destinationAirport.name,
                    passengersQuantity = passengersQuantity.toString(),
                    totalPrice = totalPrice.setScale(2, RoundingMode.HALF_EVEN).toString(),
                    customer = CustomerDTO(
                        firstName = firstName,
                        lastName = lastName,
                        documents = listOf(
                            DocumentDTO(
                                type = DocumentType.DNI.name,
                                number = documentNumber
                            )
                        ),
                        email = email,
                        phone = PhoneDTO(
                            type = PhoneType.CELL_PHONE.name,
                            areaCode = phoneNumber.substring(0, 11 - 8),
                            number = phoneNumber.takeLast(8)
                        )
                    )
                )

                postReservation(body)
            }
        }
    }

    private fun postReservation(body: CreateReservationDTO) {
        val flightReservationsApi = RetrofitClient.flightReservationsClient
        val call = flightReservationsApi.createReservation(body)

        call.enqueue(object : retrofit2.Callback<ReservationDTO> {
            override fun onResponse(call: Call<ReservationDTO>, response: Response<ReservationDTO>) {
                createdReservation = response.body()!!

                val intent = Intent(this@ProcessReservationActivity, SearchFlightsActivity::class.java)

                intent.putExtra("EXTRA_CREATED_RESERVATION", gson.toJson(createdReservation))

                startActivity(intent)
            }

            override fun onFailure(call: Call<ReservationDTO>, t: Throwable) {
                Toast.makeText(this@ProcessReservationActivity, "Error al crear la reserva", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
