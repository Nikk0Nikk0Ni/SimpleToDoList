package com.niko.data.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.niko.domain.Models.ShopItem
import com.niko.domain.Repository.ListItemRepository
import kotlin.random.Random

object ListItemRepositoryImplementation : ListItemRepository {
    private var id = 0
    private val shopList = sortedSetOf<ShopItem>({el0,el1 -> el0.id.compareTo(el1.id)})
    private val shopListLD = MutableLiveData<List<ShopItem>>()
    override fun addItem(item: ShopItem) {
        if (item.id == ShopItem.UNDEFIND_ID)
            item.id = id++
        shopList.add(item)
        updateList()
    }

    override fun editItem(item: ShopItem) {
        val oldElement = getItemById(item.id)
        removeItem(oldElement)
        addItem(item)
    }

    override fun getListItems(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    override fun removeItem(item: ShopItem) {
        shopList.remove(item)
        updateList()
    }

    override fun getItemById(id: Int): ShopItem {
        return shopList.find { it.id == id } ?: throw RuntimeException("Unknown id")
    }
    private fun updateList(){
        shopListLD.value = shopList.toList()
    }
}