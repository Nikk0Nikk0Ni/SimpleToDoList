package com.niko.domain.UseCases

import com.niko.domain.Models.ShopItem
import com.niko.domain.Repository.ListItemRepository

class RemoveItem(private val repository: ListItemRepository) {
    public fun removeItem(item : ShopItem){
        repository.removeItem(item)
    }
}