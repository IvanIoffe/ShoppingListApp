package com.ioffeivan.feature.shopping_list.data.repository

import com.ioffeivan.feature.shopping_list.domain.model.ShoppingList

// Repository responsible for performing server-side (network) operations for shopping lists.
// It is used in the background to perform deferred operations from the outbox.
interface ShoppingListSyncRepository {

    suspend fun createShoppingList(shoppingList: ShoppingList)

    suspend fun deleteShoppingList(localId: Int, serverId: Int)
}