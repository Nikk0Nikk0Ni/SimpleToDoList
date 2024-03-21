package com.niko.todoapp.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.niko.domain.Models.ShopItem
import com.niko.todoapp.R

class ShopListAdapter : ListAdapter<ShopItem,ShopListHolder>(Comporator()) {

    var longTap : ((item : ShopItem) -> Unit)? = null
    var clickTap : ((item : ShopItem) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType,parent,false)
        return ShopListHolder(view)
    }

    override fun onBindViewHolder(holder: ShopListHolder, position: Int) {
        holder.bind(getItem(position), longTap,clickTap)
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled) R.layout.enabled_note_item else R.layout.disabled_note_item
    }
}