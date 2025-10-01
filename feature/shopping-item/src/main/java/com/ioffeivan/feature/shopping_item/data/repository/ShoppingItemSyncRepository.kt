package com.ioffeivan.feature.shopping_item.data.repository

import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItem

interface ShoppingItemSyncRepository {

    suspend fun addShoppingItem(shoppingItem: ShoppingItem, listServerId: Int)

    suspend fun deleteShoppingItem(itemLocalId: Int, itemServerId: Int, listServerId: Int)
}