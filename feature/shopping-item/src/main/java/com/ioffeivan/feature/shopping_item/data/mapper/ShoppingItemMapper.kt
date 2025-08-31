package com.ioffeivan.feature.shopping_item.data.mapper

import com.ioffeivan.core.database.model.ShoppingItemEntity
import com.ioffeivan.feature.shopping_item.data.source.remote.model.ShoppingItemDto
import com.ioffeivan.feature.shopping_item.data.source.remote.model.ShoppingItemsDto
import com.ioffeivan.feature.shopping_item.domain.model.ShoppingItem

fun ShoppingItemsDto.toEntity(listId: Int): List<ShoppingItemEntity> {
    return items.map { it.toEntity(listId) }
}

fun ShoppingItemDto.toEntity(listId: Int): ShoppingItemEntity {
    return ShoppingItemEntity(
        id = id,
        name = name,
        quantity = quantity,
        listId = listId,
    )
}

fun ShoppingItemEntity.toDomain(): ShoppingItem {
    return ShoppingItem(
        id = id,
        name = name,
        quantity = quantity,
    )
}