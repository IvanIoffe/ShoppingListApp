package com.ioffeivan.feature.shopping_item.data.source.remote

import com.ioffeivan.feature.shopping_item.data.source.remote.model.AddedShoppingItemDto
import com.ioffeivan.feature.shopping_item.data.source.remote.model.ShoppingItemsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ShoppingItemApiService {

    @GET("GetShoppingList")
    suspend fun getShoppingItems(
        @Query("list_id") listId: Int,
    ): Response<ShoppingItemsDto>

    @POST("AddToShoppingList")
    suspend fun addShoppingItem(
        @Query("id") listId: Int,
        @Query("value") name: String,
        @Query("n") quantity: Int,
    ): Response<AddedShoppingItemDto>

    @POST("RemoveFromList")
    suspend fun deleteShoppingItem(
        @Query("list_id") listId: Int,
        @Query("item_id") itemId: Int,
    ): Response<Unit>
}