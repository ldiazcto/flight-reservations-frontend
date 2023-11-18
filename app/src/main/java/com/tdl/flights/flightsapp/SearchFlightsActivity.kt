package com.tdl.flights.flightsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.tdl.flights.R

class SearchFlightsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_flights)
        //arranca mi pantalla
        val btnStart = findViewById<AppCompatButton>(R.id.btnStart) //con esto engancho mi boton(el id)
        val etOrigen = findViewById<AppCompatEditText>(R.id.etOrigen) //para el id de mi editText(donde ingreo el valor)
        val etDestino = findViewById<AppCompatEditText>(R.id.etDestino)

        btnStart.setOnClickListener{//lo que quiero hacer cuando se pulse el boton
            val origen = etOrigen.text.toString()
            val destino = etDestino.text.toString()

            if(origen.isNotEmpty() and destino.isNotEmpty()){ //para moverme entre pantallas(activitys)
                val intent = Intent(this, FlightOptionsActivity::class.java)
                intent.putExtra("EXTRA_ORIGEN", origen)
                intent.putExtra("EXTRA_DESTINO",destino)
                startActivity(intent)
            }

        }

    }
}