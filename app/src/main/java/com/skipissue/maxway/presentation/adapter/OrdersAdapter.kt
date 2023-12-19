package com.skipissue.maxway.presentation.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skipissue.maxway.R
import com.skipissue.maxway.domain.entity.responses.ProductX

class OrdersAdapter : ListAdapter<ProductX, OrderViewHolder>(CharacterComparator) {
    private var onClickListener: ((Int) -> Unit)? = null
    fun setOnClickClickListener(clickListener: (Int) -> Unit) {
        onClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.products_item, parent, false)
        return OrderViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val character = getItem(position)
        character?.let { holder.bind(it) }
    }

    object CharacterComparator : DiffUtil.ItemCallback<ProductX>() {
        override fun areItemsTheSame(oldItem: ProductX, newItem: ProductX): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ProductX, newItem: ProductX): Boolean {
            return oldItem == newItem
        }
    }
}

class OrderViewHolder(view: View, val onItemClickListener: ((Int) -> Unit)?) :
    RecyclerView.ViewHolder(view) {
    private val orderId: TextView = view.findViewById(R.id.name)
    private val price: TextView = view.findViewById(R.id.price)

    fun bind(product: ProductX) {
        orderId.setText(product.name)
        price.setText(product.quantity.toString() + " x " + product.price + " so'm")
    }
}