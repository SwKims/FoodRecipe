package com.ksw.recipeclone.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Created by KSW on 2021-02-02
 */

@Database(
    entities = [RecipesEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase: RoomDatabase() {
    abstract fun recipesDao() : RecipesDAO
}