package com.niko.domain.Repository

import androidx.lifecycle.LiveData
import com.niko.domain.Models.ShopItem

interface ListItemRepository {
    suspend fun addItem(item : ShopItem)
    suspend fun editItem(item : ShopItem)
    fun getListItems() : LiveData<List<ShopItem>>
    suspend fun removeItem(item : ShopItem)
    suspend fun getItemById(id : Int) : ShopItem

}