package com.kkh.domain.di

import com.kkh.domain.repo.ArticleRepo
import com.kkh.domain.repo.ArticleRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindRepo(repoImpl: ArticleRepoImpl):ArticleRepo
}