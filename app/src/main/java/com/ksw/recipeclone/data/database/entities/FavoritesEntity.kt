package com.ksw.recipeclone.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ksw.recipeclone.models.Result
import com.ksw.recipeclone.util.Constants.Companion.FAVORITE_RECIPES_TABLE

/**
 * Created by KSW on 2021-02-05
 */

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)