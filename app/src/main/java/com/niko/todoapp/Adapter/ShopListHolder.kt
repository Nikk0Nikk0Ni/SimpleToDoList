package com.niko.todoapp.Adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.niko.domain.Models.ShopItem
import com.niko.todoapp.R

class ShopListHolder(view : View) : RecyclerView.ViewHolder(view){
    private val name = view.findViewById<TextView>(R.id.tvName)
    private val amount = view.findViewById<TextView>(R.id.tvAmount)
    fun bind(item : ShopItem, long : ((item : ShopItem) -> Unit)?, click : ((item : ShopItem) -> Unit)?){
        name.text = item.name
        amount.text = item.count.toString()
        itemView.setOnLongClickListener {
            long?.let {
                it(item)
            }
            true
        }
        itemView.setOnClickListener {
            click?.invoke(item)
        }
    }
}