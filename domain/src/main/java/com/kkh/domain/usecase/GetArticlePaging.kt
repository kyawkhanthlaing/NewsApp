package com.kkh.domain.usecase

import com.kkh.domain.repo.ArticleRepo
import javax.inject.Inject

class GetArticlePaging @Inject constructor(private val repo: ArticleRepo) {
    fun invoke(query: String) = repo.getArticlePaging(query)
}