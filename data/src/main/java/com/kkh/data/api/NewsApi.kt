package com.kkh.data.api

import com.kkh.data.response.article.ArticleDataResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/everything")
    suspend fun getNewsArticles(
        @Query(value = "language") country:String = "en",
        @Query(value = "q") query:String = "bitcoin",
        @Query(value = "page") page: Int = 1,
        @Query(value = "pageSize") pageSize: Int = 50
    ): ArticleDataResponse

}