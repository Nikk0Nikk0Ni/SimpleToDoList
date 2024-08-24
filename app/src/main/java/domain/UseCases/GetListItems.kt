package com.niko.domain.UseCases

import androidx.lifecycle.LiveData
import com.niko.domain.Models.ShopItem
import com.niko.domain.Repository.ListItemRepository
import javax.inject.Inject

class GetListItems @Inject constructor(private val repository : ListItemRepository) {
    public fun getListItems() : LiveData<List<ShopItem>> {
        return repository.getListItems()
    }
}