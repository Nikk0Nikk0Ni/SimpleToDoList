package com.niko.domain.UseCases

import com.niko.domain.Models.ShopItem
import com.niko.domain.Repository.ListItemRepository

class AddItem(private val repository: ListItemRepository){
    public fun addItem(item : ShopItem){
        repository.addItem(item)
    }
}
