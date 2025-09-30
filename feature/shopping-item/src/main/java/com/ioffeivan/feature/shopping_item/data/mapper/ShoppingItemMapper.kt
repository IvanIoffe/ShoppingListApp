package com.ioffeivan.feature.shopping_item.data.mapper

import com.ioffeivan.core.database.model.ShoppingItemEntity
import com.ioffeivan.feature.shopping_item.data.source.remote.model.AddShoppingItemDto
import com.ioffeivan.feature.shopping_item.data.source.remote.model.AddedShoppingItemDto
import com.ioffeivan.feature.shopping_item.data.source.remote.model.ShoppingItemDto
import com.ioffeivan.feature.shopping_item.data.source.remote.model.ShoppingItemsDto
import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItem

fun ShoppingItem.toAddShoppingItemDto(listId: Int): AddShoppingItemDto {
    return AddShoppingItemDto(
        name = name,
        quantity = quantity.toIntOrNull() ?: 0,
        listId = listId,
    )
}

fun ShoppingItemEntity.toDomain(): ShoppingItem {
    return ShoppingItem(
        id = id,
        name = name,
        quantity = quantity,
        listId = 0,
    )
}

fun ShoppingItemsDto.toEntities(listId: Int): List<ShoppingItemEntity> {
    return items.map { it.toEntity(listId) }
}

private fun ShoppingItemDto.toEntity(listId: Int): ShoppingItemEntity {
    return ShoppingItemEntity(
        serverId = id,
        name = name,
        quantity = quantity,
        listId = listId,
    )
}