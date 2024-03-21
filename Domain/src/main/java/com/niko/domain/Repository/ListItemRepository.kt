package com.niko.domain.Repository

import androidx.lifecycle.LiveData
import com.niko.domain.Models.ShopItem

interface ListItemRepository {
    fun addItem(item : ShopItem)
    fun editItem(item : ShopItem)
    fun getListItems() : LiveData<List<ShopItem>>
    fun removeItem(item : ShopItem)
    fun getItemById(id : Int) : ShopItem

}