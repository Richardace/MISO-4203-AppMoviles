package com.moviles.vinilos.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moviles.vinilos.R
import com.moviles.vinilos.brokers.VolleyBroker
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.ui.adapters.BandAdapter

class ArtistListView : AppCompatActivity() {
    lateinit var volleyBroker: VolleyBroker
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BandAdapter
    private lateinit var bandList: MutableList<BandModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_list_view)
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
        val addArtistButton = findViewById<Button>(R.id.addartistbutton)
        addArtistButton.setOnClickListener {
            val intent = Intent(this, AddArtistActivity::class.java)
            startActivity(intent)
        }
        recyclerView = findViewById(R.id.band_recycler_view)
        bandList = mutableListOf()
        adapter = BandAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}

