package com.ksw.recipeclone.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ksw.recipeclone.data.database.entities.FavoritesEntity
import com.ksw.recipeclone.data.database.entities.RecipesEntity

/**
 * Created by KSW on 2021-02-02
 */

/**
    RoomDatabase를 상속한다.
    @Database 어노테이션 안에 사용할 entity 클래스들을 포함시킨다.
    Singleton 패턴을 사용해야 비용을 아끼면서 데이터의 일치성을 보장할 수 있다.
    클래스 추가를 하면, 버전업을 해줘야한다.(삭제하고 다시 앱 설치후에는 버전 유지)
 */


@Database(
    entities = [RecipesEntity::class, FavoritesEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase: RoomDatabase() {
    abstract fun recipesDao() : RecipesDAO
}