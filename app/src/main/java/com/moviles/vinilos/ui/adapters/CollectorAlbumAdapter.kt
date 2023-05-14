package com.moviles.vinilos.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moviles.vinilos.databinding.AlbumRowBinding
import com.moviles.vinilos.databinding.CollectorAlbumRowBinding
import com.moviles.vinilos.databinding.CollectorRowBinding
import com.moviles.vinilos.models.CatalogoAlbumModel
import com.moviles.vinilos.models.ColeccionAlbumModel

class CollectorAlbumAdapter : RecyclerView.Adapter<CollectorAlbumViewHolder>() {
    var catalogos :List<ColeccionAlbumModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorAlbumViewHolder  {
        val withDataBinding: CollectorAlbumRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorAlbumViewHolder.LAYOUT,
            parent,
            false)
        return CollectorAlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorAlbumViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = catalogos[position]
        }
        holder.viewDataBinding.root.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return catalogos.size
    }
}
