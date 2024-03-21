package com.niko.todoapp.ViewModels

import androidx.lifecycle.ViewModel
import com.niko.data.Repository.ListItemRepositoryImplementation
import com.niko.domain.Models.ShopItem
import com.niko.domain.UseCases.EditItem
import com.niko.domain.UseCases.GetItemById
import com.niko.domain.UseCases.GetListItems
import com.niko.domain.UseCases.RemoveItem

class MainViewModel : ViewModel() {
    private val repository = ListItemRepositoryImplementation
    private val removeItem = RemoveItem(repository)
    private val editItem = EditItem(repository)
    val shopList = GetListItems(repository).getListItems()

    fun removeItem(item : ShopItem){
        removeItem.removeItem(item)
    }

    fun editItem(item : ShopItem){
        val newItem = item.copy(enabled = !item.enabled)
        editItem.editItem(newItem)
    }

}