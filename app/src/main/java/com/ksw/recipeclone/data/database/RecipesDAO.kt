package com.ksw.recipeclone.data.database

import androidx.room.*
import com.ksw.recipeclone.data.database.entities.FavoritesEntity
import com.ksw.recipeclone.data.database.entities.FoodJokeEntity
import com.ksw.recipeclone.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by KSW on 2021-02-02
 */

/**
    실제로 DB에 접근하는 객체
    @Dao 어노테이션을 가진 interface
    Data Access Object
 */

@Dao
interface RecipesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipesEntity: RecipesEntity)

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    fun readRecipes(): Flow<List<RecipesEntity>>

    // favorite
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Query("SELECT * FROM favorite_recipes_table ORDER BY id ASC")
    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>>

    @Delete
    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Query("DELETE FROM favorite_recipes_table")
    suspend fun deleteAllFavoriteRecipe()

    // joke
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity)

    @Query("SELECT * FROM food_joke_table ORDER BY id ASC")
    fun readFoodJoke(): Flow<List<FoodJokeEntity>>

}