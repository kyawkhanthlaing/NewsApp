package com.kkh.domain.repo

import androidx.paging.PagingData
import com.kkh.domain.model.ArticleItem
import kotlinx.coroutines.flow.Flow

interface ArticleRepo {
    fun getArticlePaging(query: String): Flow<PagingData<ArticleItem>>
}