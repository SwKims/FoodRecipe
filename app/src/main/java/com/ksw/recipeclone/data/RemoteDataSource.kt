package com.ksw.recipeclone.data

import com.ksw.recipeclone.data.network.FoodRecipesApi
import com.ksw.recipeclone.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by KSW on 2021-01-29
 */

// Retrofit - API - spoonacular

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {
    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

    // search add
    suspend fun searchRecipes(searchQuery: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.searchRecipes(searchQuery)
    }

}