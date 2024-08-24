package com.niko.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.niko.domain.Models.ShopItem

@Entity(tableName = "ShopItems")
data class ShopItemDBModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val count : Int,
    var enabled : Boolean
)
