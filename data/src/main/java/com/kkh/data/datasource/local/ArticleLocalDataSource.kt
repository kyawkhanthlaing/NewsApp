package com.kkh.data.datasource.local

import androidx.paging.PagingSource
import com.kkh.data.datasource.local.db.entity.ArticleEntity

interface ArticleLocalDataSource {
    fun getArticlePagingSource():PagingSource<Int,ArticleEntity>
}