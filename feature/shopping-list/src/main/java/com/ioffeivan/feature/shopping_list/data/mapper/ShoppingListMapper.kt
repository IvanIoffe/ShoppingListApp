package com.ioffeivan.feature.shopping_list.data.mapper

import com.ioffeivan.core.database.model.ShoppingListEntity
import com.ioffeivan.feature.shopping_list.data.source.remote.model.CreateShoppingListDto
import com.ioffeivan.feature.shopping_list.data.source.remote.model.CreatedShoppingListDto
import com.ioffeivan.feature.shopping_list.data.source.remote.model.ShoppingListDto
import com.ioffeivan.feature.shopping_list.data.source.remote.model.ShoppingListsDto
import com.ioffeivan.feature.shopping_list.domain.model.CreateShoppingList
import com.ioffeivan.feature.shopping_list.domain.model.ShoppingList

fun CreateShoppingList.toDto(): CreateShoppingListDto {
    return CreateShoppingListDto(
        name = name,
    )
}

fun CreatedShoppingListDto.toShoppingListEntity(
    createShoppingList: CreateShoppingList,
): ShoppingListEntity {
    return ShoppingListEntity(
        id = id,
        name = createShoppingList.name,
    )
}

fun ShoppingListEntity.toDomain(): ShoppingList {
    return ShoppingList(
        id = id,
        name = name,
        isPendingDeletion = isPendingDeletion,
    )
}

fun ShoppingListsDto.toEntity(): List<ShoppingListEntity> {
    return items.map { it.toEntity() }
}

fun ShoppingListDto.toEntity(): ShoppingListEntity {
    return ShoppingListEntity(
        id = id,
        name = name,
    )
}