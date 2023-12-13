package com.tdl.flights.flightsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.tdl.flights.R

class ProcessReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_process_reservation)
        //arranca mi pantalla
        val btnEnd = findViewById<AppCompatButton>(R.id.btnEnd) //con esto engancho mi boton(el id)
        val etNombre = findViewById<AppCompatEditText>(R.id.etNombre) //para el id de mi editText(donde ingreo el valor)
        val etApellido = findViewById<AppCompatEditText>(R.id.etApellido)
        val etDNI = findViewById<AppCompatEditText>(R.id.etDNI)

        btnEnd.setOnClickListener{//lo que quiero hacer cuando se pulse el boton
            val nombre = etNombre.text.toString()
            val apellido = etApellido.text.toString()
            val DNI = etDNI.text.toString()

            if(nombre.isNotEmpty() and apellido.isNotEmpty() and DNI.isNotEmpty()){ //para moverme entre pantallas(activitys)
                //val intent = Intent(this, FlightOptionsActivity::class.java)
                //intent.putExtra("EXTRA_NOMBRE", nombre)
                //intent.putExtra("EXTRA_APELLIDO",apellido)
                //intent.putExtra("EXTRA_DNI",DNI)
                startActivity(intent)
            }

        }
    }
}
