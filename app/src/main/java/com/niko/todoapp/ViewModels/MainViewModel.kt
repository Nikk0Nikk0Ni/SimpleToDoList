package com.niko.todoapp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niko.domain.Models.ShopItem
import com.niko.domain.UseCases.EditItem
import com.niko.domain.UseCases.GetListItems
import com.niko.domain.UseCases.RemoveItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val removeItem: RemoveItem,
    private val editItem: EditItem,
    val shopList: GetListItems
) : ViewModel() {


    fun removeItem(item: ShopItem) {
        viewModelScope.launch {
            removeItem.removeItem(item)
        }
    }

    fun editItem(item: ShopItem) {
        viewModelScope.launch {
            val newItem = item.copy(enabled = !item.enabled)
            editItem.editItem(newItem)
        }
    }
}