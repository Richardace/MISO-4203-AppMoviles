package com.moviles.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.moviles.vinilos.R
import com.moviles.vinilos.databinding.BandRowBinding
import com.moviles.vinilos.databinding.CommentRowBinding
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.models.CommentModel

class CommentAdapter : RecyclerView.Adapter<CommentViewHolder>() {
    var comments: List<CommentModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val withDataBinding: CommentRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CommentViewHolder.LAYOUT,
            parent,
            false
        )
        return CommentViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.comment = comments[position]
        }
        holder.bind(comments[position])
    }

    override fun getItemCount(): Int {
        return comments.size
    }
}

