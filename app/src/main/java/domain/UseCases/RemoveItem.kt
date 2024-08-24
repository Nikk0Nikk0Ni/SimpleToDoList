package com.niko.domain.UseCases

import com.niko.domain.Models.ShopItem
import com.niko.domain.Repository.ListItemRepository
import javax.inject.Inject

class RemoveItem @Inject constructor(private val repository: ListItemRepository) {
    public suspend fun removeItem(item : ShopItem){
        repository.removeItem(item)
    }
}