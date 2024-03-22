package com.niko.data.mappers

import com.niko.data.models.ShopItemDBModel
import com.niko.domain.Models.ShopItem

class ShopListMapper {
    fun mapEntityToDb(shopItem: ShopItem) = ShopItemDBModel(
        shopItem.id,
        shopItem.name,
        shopItem.count,
        shopItem.enabled
    )

    fun mapDbToEntity(shopItemDBModel: ShopItemDBModel) = ShopItem(
        id = shopItemDBModel.id,
        name = shopItemDBModel.name,
        count = shopItemDBModel.count,
        enabled = shopItemDBModel.enabled
    )

    fun mapListDBtoListEntity(list : List<ShopItemDBModel>) : List<ShopItem>{
        return list.map { mapDbToEntity(it) }
    }

}