package com.niko.domain.UseCases

import com.niko.domain.Models.ShopItem
import com.niko.domain.Repository.ListItemRepository

class EditItem(private val repository: ListItemRepository){
    public fun editItem(item : ShopItem){
        return repository.editItem(item)
    }
}