package com.moviles.vinilos.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moviles.vinilos.databinding.AlbumRowBinding
import com.moviles.vinilos.databinding.CollectorRowBinding
import com.moviles.vinilos.models.CatalogoAlbumModel
interface tapped {
    fun onItemtapped(catalogo: CatalogoAlbumModel)
}

class CatalogoAdapter(val callback: tapped) : RecyclerView.Adapter<CatalogoViewHolder>() {
    var catalogos :List<CatalogoAlbumModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder  {
        val withDataBinding: AlbumRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CatalogoViewHolder.LAYOUT,
            parent,
            false)
        return CatalogoViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = catalogos[position]
        }
        holder.viewDataBinding.root.setOnClickListener {
        }

        holder.itemView.setOnClickListener{
            Log.i("prueba: ", catalogos[position].name)
            callback.onItemtapped(catalogos[position])
        };
    }

    override fun getItemCount(): Int {
        return catalogos.size
    }


}
