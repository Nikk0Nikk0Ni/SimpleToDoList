package com.niko.todoapp.Adapter

import androidx.recyclerview.widget.DiffUtil
import com.niko.domain.Models.ShopItem

class Comporator : DiffUtil.ItemCallback<ShopItem>(){
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }

}