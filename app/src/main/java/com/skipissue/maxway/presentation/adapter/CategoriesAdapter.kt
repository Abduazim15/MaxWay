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
import com.skipissue.maxway.domain.entity.responses.Category

class CategoriesAdapter : ListAdapter<Category, CategoryViewHolder>(CharacterComparator) {
    private var onClickListener: ((Int) -> Unit)? = null
    fun setOnClickClickListener(clickListener: (Int) -> Unit) {
        onClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.foods_item, parent, false)
        return CategoryViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val character = getItem(position)
        character?.let { holder.bind(it) }
    }

    object CharacterComparator : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
}

class CategoryViewHolder(view: View, val onItemClickListener: ((Int) -> Unit)?) :
    RecyclerView.ViewHolder(view) {
    private val nameCat: TextView = view.findViewById(R.id.nameCat)
    private val foodsRecycler: RecyclerView = view.findViewById(R.id.foods_recycler)
    private val layout: LinearLayout = view.findViewById(R.id.layout)
    private val adapter by lazy { FoodsAdapter() }
    fun bind(category: Category) {
        nameCat.text = category.title.uz
        foodsRecycler.adapter = adapter
        adapter.submitList(category.products)
        layout.setOnClickListener {
            onItemClickListener?.invoke(bindingAdapterPosition)
        }
    }
}