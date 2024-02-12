package com.kkh.domain.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.kkh.data.datasource.local.ArticleLocalDataSource
import com.kkh.data.datasource.remote.ArticleRemoteMediator
import com.kkh.domain.model.ArticleItem
import com.kkh.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticleRepoImpl @Inject constructor(
    private val localDataSource: ArticleLocalDataSource,
    private val remoteDataSource: ArticleRemoteMediator
) : ArticleRepo {
    @OptIn(ExperimentalPagingApi::class)
    override fun getArticlePaging(query: String): Flow<PagingData<ArticleItem>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = remoteDataSource.withQuery(query),
            pagingSourceFactory = {
                localDataSource.getArticlePagingSource()
            }
        ).flow.map { paging -> paging.map { it.toDomain() } }
    }

}