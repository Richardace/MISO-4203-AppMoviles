package com.moviles.vinilos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myButton = findViewById<Button>(R.id.visitorButton)
        myButton.setOnClickListener {
           // Toast.makeText(applicationContext, "Button Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, VisitorActivity::class.java)
            startActivity(intent)
        }

    }
}