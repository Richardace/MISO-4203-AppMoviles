package com.moviles.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moviles.vinilos.R
import com.moviles.vinilos.databinding.BandRowBinding
import com.moviles.vinilos.models.BandModel

class BandAdapter : RecyclerView.Adapter<BandViewHolder>() {
    var bands :List<BandModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BandViewHolder {
        val withDataBinding: BandRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            BandViewHolder.LAYOUT,
            parent,
            false)
        return BandViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: BandViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.band = bands[position]
        }
        holder.viewDataBinding.root.setOnClickListener {
            //NOT NEEDED RIGHT NOW
            //val action = CollectorFragmentDirections.actionCollectorFragmentToAlbumFragment()
            // Navigate using that action
            //holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return bands.size
    }
}

