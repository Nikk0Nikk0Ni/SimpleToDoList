package com.niko.domain.UseCases

import com.niko.domain.Models.ShopItem
import com.niko.domain.Repository.ListItemRepository
import javax.inject.Inject

class EditItem @Inject constructor(private val repository: ListItemRepository){
    public suspend fun editItem(item : ShopItem){
        return repository.editItem(item)
    }
}