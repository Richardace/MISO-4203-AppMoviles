package com.moviles.vinilos.ui.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moviles.vinilos.R
import com.moviles.vinilos.databinding.CollectorRowBinding
import com.moviles.vinilos.databinding.CommentRowBinding
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.models.CommentModel

class CommentViewHolder(val viewDataBinding: CommentRowBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.comment_row
    }

    private val nameTextView: TextView = itemView.findViewById(R.id.commentDescription)

    fun bind(comment: CommentModel) {
        nameTextView.text = comment.description

    }
}