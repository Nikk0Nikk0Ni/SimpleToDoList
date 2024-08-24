package com.niko.domain.UseCases

import com.niko.domain.Models.ShopItem
import com.niko.domain.Repository.ListItemRepository
import javax.inject.Inject

class GetItemById @Inject constructor(private val repository: ListItemRepository) {
    public suspend fun getItemById(id : Int) : ShopItem{
        return repository.getItemById(id)
    }
}