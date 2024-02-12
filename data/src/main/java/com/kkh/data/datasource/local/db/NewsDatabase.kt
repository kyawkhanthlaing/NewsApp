package com.kkh.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kkh.data.datasource.local.db.dao.ArticleDao
import com.kkh.data.datasource.local.db.entity.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 2
)
abstract class NewsDatabase: RoomDatabase() {

    abstract val dao: ArticleDao
}