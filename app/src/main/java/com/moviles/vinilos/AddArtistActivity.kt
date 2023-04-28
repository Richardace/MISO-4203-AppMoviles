package com.moviles.vinilos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AddArtistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_artist)
        val backButton = findViewById<Button>(R.id.backButton2)
        backButton.setOnClickListener {
            finish()
        }
    }
}