package com.inventoryapp.entity

data class Item(
        var skuId: String = "",
        var itemName: String? = "",
        var manufacturerName: String? = "",
        var quantity: Int? = null
)
