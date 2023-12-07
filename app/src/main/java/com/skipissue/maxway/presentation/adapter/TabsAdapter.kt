package com.skipissue.maxway.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skipissue.maxway.R
import com.skipissue.maxway.domain.entity.TabEntity

class TabsAdapter(val list: List<TabEntity>) : RecyclerView.Adapter<TabsAdapter.TabViewHolder>() {
    private var onClickListener: ((Int) -> Unit)? = null
    var selectedItemPosition = 0
    fun setOnClickClickListener(clickListener: (Int) -> Unit) {
        onClickListener = clickListener
    }


//    object CharacterComparator : DiffUtil.ItemCallback<TabEntity>() {
//        override fun areItemsTheSame(oldItem: TabEntity, newItem: TabEntity): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        @SuppressLint("DiffUtilEquals")
//        override fun areContentsTheSame(oldItem: TabEntity, newItem: TabEntity): Boolean {
//            return oldItem == newItem
//        }
//    }

    inner class TabViewHolder(view: View, val onItemClickListener: ((Int) -> Unit)?) :
        RecyclerView.ViewHolder(view) {
        val layout: FrameLayout = view.findViewById(R.id.item)
        private val name: TextView = view.findViewById(R.id.nameItem)


        fun bind(food: TabEntity) {
            name.setText(food.title)
            layout.setOnClickListener {
                selectedItemPosition = bindingAdapterPosition
                onItemClickListener?.invoke(bindingAdapterPosition)
                notifyDataSetChanged()
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tab_item, parent, false)
        return TabViewHolder(view, onClickListener)
    }
    fun select(index: Int){
        selectedItemPosition = index
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TabViewHolder, position: Int) {
        holder.bind(list[position])
    }
}
