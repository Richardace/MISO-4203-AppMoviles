package com.moviles.vinilos.ui
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.moviles.vinilos.R


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
        backButton.setPadding(40, 40, 40, 40)
        backButton.setOnClickListener {
            finish()
        }
    }
}