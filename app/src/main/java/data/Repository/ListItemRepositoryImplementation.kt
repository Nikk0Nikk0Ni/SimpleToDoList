package com.niko.data.Repository

import android.app.Application
import android.service.autofill.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.niko.data.database.MainDB
import com.niko.data.database.MainDBDao
import com.niko.data.mappers.ShopListMapper
import com.niko.domain.Models.ShopItem
import com.niko.domain.Repository.ListItemRepository
import javax.inject.Inject
import kotlin.random.Random

class ListItemRepositoryImplementation @Inject constructor(
    private var shopListDao: MainDBDao,
    private val mapper: ShopListMapper
) : ListItemRepository {
    override suspend fun addItem(item: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDb(item))
    }

    override suspend fun editItem(item: ShopItem) {
        addItem(item)
    }

    override fun getListItems(): LiveData<List<ShopItem>> = shopListDao.getShopList().map {
        mapper.mapListDBtoListEntity(it)
    }

    override suspend fun removeItem(item: ShopItem) {
        shopListDao.deletShopItem(item.id)
    }

    override suspend fun getItemById(id: Int): ShopItem {
        return mapper.mapDbToEntity(shopListDao.getShopItem(id))
    }
}