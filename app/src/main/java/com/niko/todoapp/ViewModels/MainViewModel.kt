package com.niko.todoapp.ViewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niko.data.Repository.ListItemRepositoryImplementation
import com.niko.domain.Models.ShopItem
import com.niko.domain.UseCases.EditItem
import com.niko.domain.UseCases.GetItemById
import com.niko.domain.UseCases.GetListItems
import com.niko.domain.UseCases.RemoveItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : ViewModel() {
    private val repository = ListItemRepositoryImplementation(application)
    private val removeItem = RemoveItem(repository)
    private val editItem = EditItem(repository)

    val shopList = GetListItems(repository).getListItems()

    fun removeItem(item : ShopItem){
        viewModelScope.launch {
            removeItem.removeItem(item)
        }
    }

    fun editItem(item : ShopItem){
        viewModelScope.launch {
            val newItem = item.copy(enabled = !item.enabled)
            editItem.editItem(newItem)
        }
    }
}