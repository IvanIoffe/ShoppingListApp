package com.ioffeivan.feature.shopping_list.data.mapper

import com.ioffeivan.core.database.model.ShoppingListEntity
import com.ioffeivan.feature.shopping_list.data.source.remote.model.CreateShoppingListDto
import com.ioffeivan.feature.shopping_list.data.source.remote.model.CreatedShoppingListDto
import com.ioffeivan.feature.shopping_list.data.source.remote.model.ShoppingListDto
import com.ioffeivan.feature.shopping_list.data.source.remote.model.ShoppingListsDto
import com.ioffeivan.feature.shopping_list.domain.model.CreateShoppingList
import com.ioffeivan.feature.shopping_list.domain.model.ShoppingList

fun CreateShoppingList.toShoppingListEntity(): ShoppingListEntity {
    return ShoppingListEntity(
        name = name,
    )
}

fun ShoppingList.toCreateShoppingListDto(): CreateShoppingListDto {
    return CreateShoppingListDto(
        name = name,
    )
}

fun CreatedShoppingListDto.toShoppingListEntity(
    shoppingList: ShoppingList,
): ShoppingListEntity {
    return ShoppingListEntity(
        id = shoppingList.id,
        serverId = this.id,
        name = shoppingList.name,
    )
}

fun ShoppingListEntity.toDomain(): ShoppingList {
    return ShoppingList(
        id = id,
        name = name,
        serverId = serverId,
    )
}

fun ShoppingListsDto.toShoppingListEntities(): List<ShoppingListEntity> {
    return items.map(ShoppingListDto::toShoppingListEntity)
}

private fun ShoppingListDto.toShoppingListEntity(): ShoppingListEntity {
    return ShoppingListEntity(
        serverId = id,
        name = name,
    )
}