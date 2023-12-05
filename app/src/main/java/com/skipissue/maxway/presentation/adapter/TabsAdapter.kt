package com.skipissue.maxway.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skipissue.maxway.R
import com.skipissue.maxway.domain.entity.TabEntity

class TabsAdapter : ListAdapter<TabEntity, TabsAdapter.TabViewHolder>(CharacterComparator) {
    private var onClickListener: ((Int) -> Unit)? = null
    private var selectedItemPosition = RecyclerView.NO_POSITION
    fun setOnClickClickListener(clickListener: (Int) -> Unit) {
        onClickListener = clickListener
    }
    fun update(){
        submitList(currentList)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.tab_item, parent, false)
        return TabViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: TabViewHolder, position: Int) {
        val character = getItem(position)
        character?.let { holder.bind(it) }
    }

    object CharacterComparator : DiffUtil.ItemCallback<TabEntity>() {
        override fun areItemsTheSame(oldItem: TabEntity, newItem: TabEntity): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: TabEntity, newItem: TabEntity): Boolean {
            return oldItem == newItem
        }
    }

    inner class TabViewHolder(view: View, val onItemClickListener: ((Int) -> Unit)?) :
        RecyclerView.ViewHolder(view) {
        val layout: FrameLayout = view.findViewById(R.id.item)
        private val name: TextView = view.findViewById(R.id.nameItem)


        fun bind(food: TabEntity) {
            name.setText(food.title)
            layout.setOnClickListener {
                selectedItemPosition = bindingAdapterPosition
                update()
                onItemClickListener?.invoke(bindingAdapterPosition)
            }
            if (selectedItemPosition == bindingAdapterPosition){
                layout.setBackgroundResource(R.drawable.purple_square)
                name.setTextColor(Color.WHITE)
            }
            else{
                layout.setBackgroundResource(R.drawable.white_square)
                name.setTextColor(Color.BLACK)
            }
        }
    }
}
