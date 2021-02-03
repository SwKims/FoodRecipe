package com.ksw.recipeclone.data.network

import com.ksw.recipeclone.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by KSW on 2021-01-29
 */

interface FoodRecipesApi {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries : Map<String, String>
    ) : Response<FoodRecipe>

    // search add.
    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(
        @QueryMap searchQuery : Map<String, String>
    ) : Response<FoodRecipe>

}