package com.niko.todoapp.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.niko.data.Repository.ListItemRepositoryImplementation
import com.niko.domain.Models.ShopItem
import com.niko.domain.UseCases.AddItem
import com.niko.domain.UseCases.EditItem
import com.niko.domain.UseCases.GetItemById
import java.lang.Exception

class AddEditViewModel : ViewModel() {
    private val repository = ListItemRepositoryImplementation
    private val addItem = AddItem(repository)
    private val editItem = EditItem(repository)
    private val getItemById = GetItemById(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputAmount = MutableLiveData<Boolean>()
    val errorInputAmount: LiveData<Boolean>
        get() = _errorInputAmount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _isEnd = MutableLiveData<Unit>()
    val isEnd: LiveData<Unit>
        get() = _isEnd

    fun addItem(inputName: String?, inputAmount: String?) {
        val name = parseName(inputName)
        val amount = parseAmount(inputAmount)
        if (validateInput(name, amount)) {
            addItem.addItem(ShopItem(name, amount, true))
            _isEnd.value = Unit
            Log.e("ADD","+")
        }
    }

    fun editItem(inputName: String?, inputAmount: String?) {
        val name = parseName(inputName)
        val amount = parseAmount(inputAmount)
        if (validateInput(name, amount)) {
            _shopItem.value?.let { editItem.editItem(it.copy(name, amount)) }
            _isEnd.value = Unit
        }

    }

    fun getShopItem(id: Int) {
        val item = getItemById.getItemById(id)
        _shopItem.value = item

    }

    private fun parseName(name: String?): String {
        return name?.trim() ?: ""
    }

    private fun parseAmount(amount: String?): Int {
        return try {
            amount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, amount: Int): Boolean {
        var result = true
        if (name.isEmpty()) {
            _errorInputName.value = true
            result = false
        }
        if (amount <= 0) {
            _errorInputAmount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputAmount() {
        _errorInputAmount.value = false
    }


}