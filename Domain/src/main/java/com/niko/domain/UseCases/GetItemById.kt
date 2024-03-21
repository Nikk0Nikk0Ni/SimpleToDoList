package com.niko.domain.UseCases

import com.niko.domain.Models.ShopItem
import com.niko.domain.Repository.ListItemRepository

class GetItemById(private val repository: ListItemRepository) {
    public fun getItemById(id : Int) : ShopItem{
        return repository.getItemById(id)
    }
}