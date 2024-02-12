package com.kkh.domain.model

import com.kkh.data.datasource.local.db.entity.ArticleEntity
import java.io.Serializable

data class ArticleItem(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
): Serializable

fun ArticleEntity.toDomain(): ArticleItem{
    return ArticleItem(
        author=author,
        content=content,
        description=description,
        publishedAt=publishedAt,
        title=title,
        url=url,
        urlToImage=urlToImage
    )
}