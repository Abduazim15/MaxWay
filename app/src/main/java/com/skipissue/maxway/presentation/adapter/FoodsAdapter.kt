package com.skipissue.maxway.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skipissue.maxway.R
import com.skipissue.maxway.domain.entity.responses.Product


class FoodsAdapter : ListAdapter<Product, FoodsViewHolder>(CharacterComparator) {
    private var onClickListener: ((Int) -> Unit)? = null
    fun setOnClickClickListener(clickListener: (Int) -> Unit) {
        onClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return FoodsViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {
        val character = getItem(position)
        character?.let { holder.bind(it) }
    }

    object CharacterComparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}

class FoodsViewHolder(view: View, val onItemClickListener: ((Int) -> Unit)?) :
    RecyclerView.ViewHolder(view) {
    val layout : LinearLayout = view.findViewById(R.id.item)
    private val name: TextView = view.findViewById(R.id.name)
    private val cost: TextView = view.findViewById(R.id.cost)
    private val description: TextView = view.findViewById(R.id.description)
    private val image: ImageView = view.findViewById(R.id.image)

    fun bind(food: Product) {
        cost.setText("${food.out_price} ${food.currency}")
        name.setText(food.title.uz)
        description.setText(food.description.uz)

        layout.setOnClickListener {
            onItemClickListener?.invoke(bindingAdapterPosition)
        }
    }
}