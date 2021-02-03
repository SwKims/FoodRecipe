package com.ksw.recipeclone.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ksw.recipeclone.models.FoodRecipe

/**
 * Created by KSW on 2021-02-02
 */

/**
 * 기본자료형이 아닌 객체를 사용하기 위해선 TypeConverter를 사용해야 한다. room
 */

class RecipesTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe) : String {
        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun stringToFoodRecipe(data: String) : FoodRecipe {
        val listType = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(data, listType)
    }
}