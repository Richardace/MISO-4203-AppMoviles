package com.moviles.vinilos.ui.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moviles.vinilos.R
import com.moviles.vinilos.databinding.BandRowBinding
import com.moviles.vinilos.models.BandModel


class BandViewHolder(val viewDataBinding: BandRowBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.band_row
    }

    private val nameTextView: TextView = itemView.findViewById(R.id.bandTitle)
    private val imageImageView: ImageView = itemView.findViewById(R.id.bandImage)
    fun bind(band: BandModel) {
        nameTextView.text = band.name
        Glide.with(itemView)
            .load(band.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(imageImageView)
    }
}