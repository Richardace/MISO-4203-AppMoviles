package com.moviles.vinilos.ui.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moviles.vinilos.R
import com.moviles.vinilos.databinding.AlbumRowBinding
import com.moviles.vinilos.databinding.CollectorRowBinding
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.models.CatalogoAlbumModel

class CatalogoViewHolder(val viewDataBinding: AlbumRowBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.album_row
    }

    private val nameTextView: TextView = itemView.findViewById(R.id.albumTitle)
    private val imageImageView: ImageView = itemView.findViewById(R.id.albumImage)
    fun bind(band: CatalogoAlbumModel) {
        nameTextView.text = band.name
        Glide.with(itemView)
            .load(band.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(imageImageView)
    }
}