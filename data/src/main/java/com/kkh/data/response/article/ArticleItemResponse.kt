package com.kkh.data.response.article

import com.kkh.data.datasource.local.db.entity.ArticleEntity

data class ArticleItemResponse(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)

fun ArticleItemResponse.toArticleEntity():ArticleEntity{
    return ArticleEntity(
        author = author.orEmpty(),
        content = content.orEmpty(),
        description = description.orEmpty(),
        publishedAt = publishedAt.orEmpty(),
        title = title.orEmpty(),
        url = url.orEmpty(),
        urlToImage = urlToImage.orEmpty()
    )
}