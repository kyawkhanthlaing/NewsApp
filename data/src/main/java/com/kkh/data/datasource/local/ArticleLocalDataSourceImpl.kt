package com.kkh.data.datasource.local

import androidx.paging.PagingSource
import com.kkh.data.datasource.local.db.dao.ArticleDao
import com.kkh.data.datasource.local.db.entity.ArticleEntity
import javax.inject.Inject

class ArticleLocalDataSourceImpl @Inject constructor(private val dao: ArticleDao) : ArticleLocalDataSource {
    override fun getArticlePagingSource(): PagingSource<Int, ArticleEntity> {
        return dao.pagingSource()
    }
}