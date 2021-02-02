package com.ksw.recipeclone.data

import com.ksw.recipeclone.data.database.RecipesDAO
import com.ksw.recipeclone.data.database.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by KSW on 2021-02-02
 */

// ROOM - SQLite

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDAO
){
    fun readDatabase() : Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }
    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipes(recipesEntity)
    }
}