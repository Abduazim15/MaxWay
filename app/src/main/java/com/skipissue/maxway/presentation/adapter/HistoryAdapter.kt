package com.skipissue.maxway.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skipissue.maxway.R
import com.skipissue.maxway.domain.entity.responses.Order

class HistoryAdapter : ListAdapter<Order, HistoryViewHolder>(CharacterComparator) {
    private var onClickListener: ((Int) -> Unit)? = null
    fun setOnClickClickListener(clickListener: (Int) -> Unit) {
        onClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return HistoryViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val character = getItem(position)
        character?.let { holder.bind(it) }
    }

    object CharacterComparator : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem == newItem
        }
    }
}

class HistoryViewHolder(view: View, val onItemClickListener: ((Int) -> Unit)?) :
    RecyclerView.ViewHolder(view) {
    val layout : LinearLayout = view.findViewById(R.id.item)
    private val orderId: TextView = view.findViewById(R.id.orderId)
    private val price: TextView = view.findViewById(R.id.price)
    private val status: TextView = view.findViewById(R.id.status)
    private val date: TextView = view.findViewById(R.id.date)

    fun bind(order: Order) {
        orderId.setText("Buyurtma No${order.external_order_id}")
        price.setText("${(order.order_amount + order.co_delivery_price)} so'm")
        status.setText(if (order.paid) "Muvaffaqiyatli" else "Buyurtma bekor qilindi")
        val createdAt = order.created_at.substring(0, order.created_at.indexOf(" "))
        date.setText(createdAt.replace("-", "."))
        layout.setOnClickListener {
            onItemClickListener?.invoke(bindingAdapterPosition)
        }
    }
}