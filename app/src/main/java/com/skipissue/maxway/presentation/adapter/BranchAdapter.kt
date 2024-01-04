package com.skipissue.maxway.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skipissue.maxway.R
import com.skipissue.maxway.domain.entity.responses.Branche

class BranchAdapter : ListAdapter<Branche, BranchViewHolder>(CharacterComparator) {
    private var onClickListener: ((Int) -> Unit)? = null
    fun setOnClickClickListener(clickListener: (Int) -> Unit) {
        onClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.branche_item, parent, false)
        return BranchViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: BranchViewHolder, position: Int) {
        val character = getItem(position)
        character?.let { holder.bind(it) }
    }

    object CharacterComparator : DiffUtil.ItemCallback<Branche>() {
        override fun areItemsTheSame(oldItem: Branche, newItem: Branche): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Branche, newItem: Branche): Boolean {
            return oldItem == newItem
        }
    }
}

class BranchViewHolder(view: View, val onItemClickListener: ((Int) -> Unit)?) :
    RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.name)
    private val layout: ConstraintLayout = view.findViewById(R.id.item)
    private val description: TextView = view.findViewById(R.id.description)

    fun bind(branch: Branche) {
        name.setText(branch.name)
        description.setText(branch.destination)
        layout.setOnClickListener {
            onItemClickListener?.invoke(bindingAdapterPosition)
        }
    }
}