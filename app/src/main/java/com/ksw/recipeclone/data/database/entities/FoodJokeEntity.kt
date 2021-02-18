package com.ksw.recipeclone.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ksw.recipeclone.models.FoodJoke
import com.ksw.recipeclone.util.Constants.Companion.FOOD_JOKE_TABLE

/**
 * Created by KSW on 2021-02-17
 */

@Entity(tableName = FOOD_JOKE_TABLE)
class FoodJokeEntity(
    @Embedded
    var foodJoke: FoodJoke
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}