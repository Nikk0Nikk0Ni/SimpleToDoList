package com.niko.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.niko.data.models.ShopItemDBModel

@Dao
interface MainDBDao {

    @Query("SELECT * FROM ShopItems")
    fun getShopList() : LiveData<List<ShopItemDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopItem(shopItemDBModel: ShopItemDBModel)

    @Query("DELETE FROM ShopItems WHERE id = :shopItemId")
    suspend fun deletShopItem(shopItemId: Int)

    @Query("SELECT * FROM ShopItems WHERE id = :shopItemId LIMIT 1")
    suspend fun getShopItem(shopItemId : Int) : ShopItemDBModel
}