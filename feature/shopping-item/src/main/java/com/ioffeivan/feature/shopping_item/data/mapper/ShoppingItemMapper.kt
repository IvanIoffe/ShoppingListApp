package com.ioffeivan.feature.shopping_item.data.mapper

import com.ioffeivan.core.database.model.ShoppingItemEntity
import com.ioffeivan.feature.shopping_item.data.source.remote.model.AddShoppingItemDto
import com.ioffeivan.feature.shopping_item.data.source.remote.model.AddedShoppingItemDto
import com.ioffeivan.feature.shopping_item.data.source.remote.model.DeleteShoppingItemDto
import com.ioffeivan.feature.shopping_item.data.source.remote.model.ShoppingItemDto
import com.ioffeivan.feature.shopping_item.data.source.remote.model.ShoppingItemsDto
import com.ioffeivan.feature.shopping_item.domain.model.AddShoppingItem
import com.ioffeivan.feature.shopping_item.domain.model.DeleteShoppingItem
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

fun DeleteShoppingItem.toDto(): DeleteShoppingItemDto {
    return DeleteShoppingItemDto(
        listId = listId,
        itemId = itemId,
    )
}

fun AddShoppingItem.toDto(): AddShoppingItemDto {
    return AddShoppingItemDto(
        name = name,
        quantity = quantity,
        listId = listId,
    )
}

fun AddedShoppingItemDto.toShoppingItemEntity(
    addShoppingItem: AddShoppingItem,
): ShoppingItemEntity {
    return ShoppingItemEntity(
        id = id,
        name = addShoppingItem.name,
        quantity = addShoppingItem.quantity.toString(),
        listId = addShoppingItem.listId,
    )
}