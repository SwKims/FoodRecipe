package com.ksw.recipeclone.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ksw.recipeclone.models.FoodRecipe
import com.ksw.recipeclone.util.Constants.Companion.RECIPES_TABLE

/**
 * Created by KSW on 2021-02-02
 */

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipes : FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id : Int = 0
}