package com.niko.domain.UseCases

import com.niko.domain.Models.ShopItem
import com.niko.domain.Repository.ListItemRepository
import javax.inject.Inject

class AddItem @Inject constructor(private val repository: ListItemRepository){
    suspend fun addItem(item : ShopItem){
        repository.addItem(item)
    }
}
