package com.moviles.vinilos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.android.volley.Response
import com.google.gson.Gson
import com.moviles.vinilos.brokers.VolleyBroker
import org.json.JSONObject

class AddArtistActivity : AppCompatActivity() {
    lateinit var volleyBroker: VolleyBroker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_artist)
        val backButton = findViewById<Button>(R.id.backButton2)
        backButton.setOnClickListener {
            finish()
        }
        val saveButton = findViewById<Button>(R.id.SaveArtistbutton)
        saveButton.setOnClickListener {
            SaveArtistApi()
        }
    }

    private fun SaveArtistApi() {
        val name = findViewById<EditText>(R.id.nombreArtista).text.toString()
        val image = findViewById<EditText>(R.id.imagenArtista).text.toString()
        val description = findViewById<EditText>(R.id.descripcionArtista).text.toString()
        val birthdate = findViewById<EditText>(R.id.birthDateArtista).text.toString()

        val jsonObject = JSONObject()
        // Add the fields
        jsonObject.put("name", name)
        jsonObject.put("image", image)
        jsonObject.put("description", description)
        jsonObject.put("birthDate", birthdate)


        volleyBroker = VolleyBroker(this.applicationContext)
        volleyBroker.instance.add(
            VolleyBroker.postRequest("musicians",jsonObject,
                Response.Listener<JSONObject> { response ->
                Log.d("REQ", response.toString())

            },
            Response.ErrorListener
            {
                Log.d("TAG", it.toString())
            }
        ))
        TODO("Not yet implemented")
    }

}