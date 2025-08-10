package com.ioffeivan.feature.shopping_list.data.source.remote

import com.ioffeivan.feature.shopping_list.data.source.remote.model.CreatedShoppingListDto
import com.ioffeivan.feature.shopping_list.data.source.remote.model.ShoppingListsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ShoppingListApiService {

    @GET("GetAllMyShopLists")
    suspend fun getAllShoppingLists(): Response<ShoppingListsDto>

    @POST("CreateShoppingList")
    suspend fun createShoppingList(
        @Query("name") listName: String,
    ): Response<CreatedShoppingListDto>

    @POST("RemoveShoppingList")
    suspend fun deleteShoppingList(
        @Query("list_id") listId: Int,
    ): Response<Unit>
}