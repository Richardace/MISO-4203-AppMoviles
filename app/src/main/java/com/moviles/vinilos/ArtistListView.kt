package com.moviles.vinilos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.moviles.vinilos.brokers.VolleyBroker

class ArtistListView : AppCompatActivity() {
    lateinit var volleyBroker: VolleyBroker
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BandAdapter
    private lateinit var bandList: MutableList<Band>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_list_view)
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
        // Initialize views
        recyclerView = findViewById(R.id.band_recycler_view)
        // Set up the RecyclerView
        bandList = mutableListOf()
        adapter = BandAdapter(bandList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        fetchJsonData()
    }
    private fun fetchJsonData() {
        volleyBroker = VolleyBroker(this.applicationContext)
        volleyBroker.instance.add(VolleyBroker.getRequest("bands",
            { response ->
                Log.d("REQ", response)
                val bands = Gson().fromJson(response, Array<Band>::class.java).toList()
                bandList.addAll(bands);
                Log.d("TABLA",bands.toString());
                adapter.notifyDataSetChanged()
            },
            {
                Log.d("TAG", it.toString())

            }
        ))
    }
}
data class Band(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val creationDate: String,
    val albums: List<Album>,
    val musicians: List<Musician>,
    val performerPrizes: List<PerformerPrize>
)

data class Album(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String
)

data class Musician(
    val id: Int,
    val name: String,
    val instrument: String
)

data class PerformerPrize(
    val id: Int,
    val premiationDate: String
)

class BandAdapter(private val bands: List<Band>) : RecyclerView.Adapter<BandViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BandViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.band_row, parent, false)
        return BandViewHolder(view)
    }

    override fun onBindViewHolder(holder: BandViewHolder, position: Int) {
        val band = bands[position]
        holder.bind(band)
    }

    override fun getItemCount(): Int {
        return bands.size
    }
}

class BandViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameTextView: TextView = itemView.findViewById(R.id.bandTitle)
    private val imageImageView: ImageView = itemView.findViewById(R.id.bandImage)

    // add more views here for each property in the Band class

    fun bind(band: Band) {
        nameTextView.text = band.name
        Glide.with(itemView)
            .load(band.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(imageImageView)
    }
}
