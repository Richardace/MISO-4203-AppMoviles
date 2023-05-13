package com.moviles.vinilos.ui.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moviles.vinilos.R
import com.moviles.vinilos.databinding.AlbumRowBinding
import com.moviles.vinilos.databinding.CollectorAlbumRowBinding
import com.moviles.vinilos.databinding.CollectorRowBinding
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.models.CatalogoAlbumModel
import com.moviles.vinilos.models.ColeccionAlbumModel

class CollectorAlbumViewHolder(val viewDataBinding: CollectorAlbumRowBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.collector_album_row
    }

    private val nameTextView: TextView = itemView.findViewById(R.id.albumTitle)
    fun bind(band: ColeccionAlbumModel) {
        nameTextView.text = band.album.name
    }
}