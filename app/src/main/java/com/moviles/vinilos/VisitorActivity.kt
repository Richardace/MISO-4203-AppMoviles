package com.moviles.vinilos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class VisitorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visitor)
        val myButton = findViewById<Button>(R.id.artistButton)
        myButton.setOnClickListener {
            // Toast.makeText(applicationContext, "Button Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ArtistListView::class.java)
            startActivity(intent)
        }
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}