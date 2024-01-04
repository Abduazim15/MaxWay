package com.skipissue.maxway.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skipissue.maxway.R
import com.skipissue.maxway.domain.entity.FoodHistoryEntity

class BasketAdapter : ListAdapter<FoodHistoryEntity, BasketViewHolder>(CharacterComparator) {
    private var onClickListener: ((Int) -> Unit)? = null
    private var onDecClickListener: ((Int, Int) -> Unit)? = null
    private var onIncClickListener: ((Int, Int) -> Unit)? = null
    fun setOnClickClickListener(clickListener: (Int) -> Unit) {
        onClickListener = clickListener
    }
    fun setOnDecClickClickListener(clickListener: (Int, Int) -> Unit) {
        onDecClickListener = clickListener
    }
    fun setOnIncClickClickListener(clickListener: (Int, Int) -> Unit) {
        onIncClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.basket_item, parent, false)
        return BasketViewHolder(view, onClickListener, onDecClickListener, onIncClickListener)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val character = getItem(position)
        character?.let { holder.bind(it) }
    }

    object CharacterComparator : DiffUtil.ItemCallback<FoodHistoryEntity>() {
        override fun areItemsTheSame(oldItem: FoodHistoryEntity, newItem: FoodHistoryEntity): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: FoodHistoryEntity, newItem: FoodHistoryEntity): Boolean {
            return oldItem == newItem
        }
    }
}

class BasketViewHolder(view: View, val onItemClickListener: ((Int) -> Unit)?, val onItemDecClickListener: ((Int, Int) -> Unit)?, val onItemIncClickListener: ((Int, Int) -> Unit)?) :
    RecyclerView.ViewHolder(view) {
    val layout: ConstraintLayout = view.findViewById(R.id.item)
    private val name: TextView = view.findViewById(R.id.name)
    private val cost: TextView = view.findViewById(R.id.price)
    private val image: ImageView = view.findViewById(R.id.image)
    private val amount: TextView = view.findViewById(R.id.amount)
    private val dec: ImageView = view.findViewById(R.id.dec)
    private val inc: ImageView = view.findViewById(R.id.inc)
    private var quantity = 0

    fun bind(food: FoodHistoryEntity) {
        quantity = food.quantity
        name.setText(food.name)
        cost.setText(food.price.toString() + " so'm")
        Glide.with(image).load("https://cdn.delever.uz/delever/" + food.imageId).into(image)
        amount.setText(food.quantity.toString())
        layout.setOnClickListener {
            onItemClickListener?.invoke(bindingAdapterPosition)
        }
        dec.setOnClickListener {
            if (quantity > 1) {
                quantity -= 1
                amount.setText(quantity.toString())
            }
            onItemDecClickListener?.invoke(bindingAdapterPosition, quantity)
        }
        inc.setOnClickListener {
            if (quantity < 100) {
                quantity += 1
                amount.setText(quantity.toString())
            }
            onItemIncClickListener?.invoke(bindingAdapterPosition, quantity)
        }
    }
}