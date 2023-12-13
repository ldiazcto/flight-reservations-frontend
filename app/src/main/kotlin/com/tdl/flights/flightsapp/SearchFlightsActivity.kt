package com.tdl.flights.flightsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.tdl.flights.R

import java.text.SimpleDateFormat
import java.util.*

class SearchFlightsActivity : AppCompatActivity() {
    lateinit var etDate: AppCompatEditText
    lateinit var btnStart: AppCompatButton
    lateinit var etOrigen: AppCompatEditText
    lateinit var etDestino: AppCompatEditText

    var isDatePickerSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_flights)


        //arranca mi pantalla
        etDate = findViewById<AppCompatEditText>(R.id.etDate)

        btnStart = findViewById<AppCompatButton>(R.id.btnStart) //con esto engancho mi boton(el id)
        etOrigen = findViewById<AppCompatEditText>(R.id.etOrigen) //para el id de mi editText(donde ingreo el valor)
        etDestino = findViewById<AppCompatEditText>(R.id.etDestino)


        etDate.setOnClickListener { ShowDatePickerDialog() }

        btnStart.setOnClickListener{//lo que quiero hacer cuando se pulse el boton
            val origen = etOrigen.text.toString()
            val destino = etDestino.text.toString()

            if(isDatePickerSelected and origen.isNotEmpty() and destino.isNotEmpty()) { //para moverme entre pantallas(activitys)
                val intent = Intent(this, FlightOptionsActivity::class.java)
                intent.putExtra("EXTRA_ORIGEN", origen)
                intent.putExtra("EXTRA_DESTINO", destino)
                startActivity(intent)
            }
        }
    }


    private fun ShowDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "dataPicker")

    }


    fun onDateSelected(day: Int, month: Int, year: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)

        val dateFormat = SimpleDateFormat("d 'de' MMMM 'del' yyyy", Locale("es", "ES"))
        val formattedDate = dateFormat.format(calendar.time)

        etDate.setText(formattedDate)
        isDatePickerSelected = true
    }
}

