package com.niko.domain.Models

data class ShopItem(
    val name : String,
    val count : Int,
    var enabled : Boolean,
    var id : Int = UNDEFIND_ID
)
{
    companion object{
        const val UNDEFIND_ID = -1
    }
}
