package com.ksw.recipeclone.models

import com.google.gson.annotations.SerializedName

/**
 * Created by KSW on 2021-02-01
 */

data class FoodRecipe(
    @SerializedName("results")
    val results: List<Result>
)
