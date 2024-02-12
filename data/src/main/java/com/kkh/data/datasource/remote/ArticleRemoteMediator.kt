package com.kkh.data.datasource.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.kkh.data.api.NewsApi
import com.kkh.data.datasource.local.db.NewsDatabase
import com.kkh.data.datasource.local.db.entity.ArticleEntity
import com.kkh.data.response.error.ApiError
import com.kkh.data.response.article.toArticleEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ArticleRemoteMediator @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDb: NewsDatabase,
) : RemoteMediator<Int, ArticleEntity>() {
    private var query: String = ""
    private var currentPage: Int = 1

    fun withQuery(query: String): ArticleRemoteMediator {
        this.query = query
        return this
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>,
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    currentPage = 1
                    1
                }
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        currentPage = 1
                        1
                    } else {
                        currentPage+1
                    }
                }
            }

            val data = newsApi.getNewsArticles(
                page = loadKey,
                pageSize = 10,
                query = query
            )

            newsDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    newsDb.dao.clearAll()
                }
                val articleEntities = data.articles.map { it.toArticleEntity() }
                newsDb.dao.upsertAll(articleEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = data.articles.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(ApiError.ConnectionError())
        } catch (e: HttpException) {
            MediatorResult.Error(
                when(e.code()){
                    400 -> ApiError.BadRequestError()
                    404-> ApiError.NotFoundError()
                    500-> ApiError.SeverError()
                    else -> ApiError.GenericError()
                }
            )
        }
    }

}

