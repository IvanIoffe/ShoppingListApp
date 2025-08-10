package com.ioffeivan.feature.shopping_list.data.mapper

import com.ioffeivan.feature.shopping_list.data.source.remote.model.CreateShoppingListDto
import com.ioffeivan.feature.shopping_list.data.source.remote.model.CreatedShoppingListDto
import com.ioffeivan.feature.shopping_list.data.source.remote.model.ShoppingListDto
import com.ioffeivan.feature.shopping_list.data.source.remote.model.ShoppingListsDto
import com.ioffeivan.feature.shopping_list.domain.model.CreateShoppingList
import com.ioffeivan.feature.shopping_list.domain.model.ShoppingList
import com.ioffeivan.feature.shopping_list.domain.model.ShoppingLists

fun ShoppingListsDto.toDomain(): ShoppingLists {
    return ShoppingLists(
        items = items.map { it.toDomain() }
    )
}

fun ShoppingListDto.toDomain(): ShoppingList {
    return ShoppingList(
        id = id,
        name = name,
    )
}

fun CreateShoppingList.toDto(): CreateShoppingListDto {
    return CreateShoppingListDto(
        name = name,
    )
}

fun CreatedShoppingListDto.toShoppingListDomain(name: String): ShoppingList {
    return ShoppingList(
        id = id,
        name = name,
    )
}

fun ShoppingList.toDto(): ShoppingListDto {
    return ShoppingListDto(
        id = id,
        name = name,
    )
}