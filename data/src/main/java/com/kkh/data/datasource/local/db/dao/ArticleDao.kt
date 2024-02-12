package com.kkh.data.datasource.local.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kkh.data.datasource.local.db.entity.ArticleEntity

@Dao
interface ArticleDao {

    @Upsert
    suspend fun insertAll(articles: List<ArticleEntity>)

    @Query("SELECT * FROM Article")
    fun pagingSource(): PagingSource<Int, ArticleEntity>

    @Query("DELETE FROM Article")
    suspend fun clearAll()
}