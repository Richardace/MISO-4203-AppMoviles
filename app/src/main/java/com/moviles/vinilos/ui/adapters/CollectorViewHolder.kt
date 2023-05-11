package com.moviles.vinilos.ui.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moviles.vinilos.R
import com.moviles.vinilos.databinding.BandRowBinding
import com.moviles.vinilos.databinding.CollectorRowBinding
import com.moviles.vinilos.models.BandModel

class CollectorViewHolder(val viewDataBinding: CollectorRowBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.collector_row
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