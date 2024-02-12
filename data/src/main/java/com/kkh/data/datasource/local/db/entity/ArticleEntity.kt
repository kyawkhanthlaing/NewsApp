package com.kkh.data.datasource.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Article"
)
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
)