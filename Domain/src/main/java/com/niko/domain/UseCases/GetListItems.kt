package com.niko.domain.UseCases

import androidx.lifecycle.LiveData
import com.niko.domain.Models.ShopItem
import com.niko.domain.Repository.ListItemRepository

class GetListItems(private val repository : ListItemRepository) {
    public fun getListItems() : LiveData<List<ShopItem>> {
        return repository.getListItems()
    }
}