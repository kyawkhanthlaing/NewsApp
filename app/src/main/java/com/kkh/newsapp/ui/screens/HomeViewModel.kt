package com.kkh.newsapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kkh.domain.model.ArticleItem
import com.kkh.domain.usecase.GetArticlePaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.debounce
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getArticlePaging: GetArticlePaging
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _articles: MutableStateFlow<PagingData<ArticleItem>> =
        MutableStateFlow(PagingData.empty())
    val articles: StateFlow<PagingData<ArticleItem>>
        get() = _articles


    init {
        observeSearchTextChanges()
        getArticlePaging.invoke("bitcoin")
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchTextChanges() {
        viewModelScope.launch {
            _searchText
                .debounce(1000L)
                .collect { text ->
                    if (text.isBlank()) {
                        getArticles("bitcoin")
                    } else
                        getArticles(text)
                }
        }
    }

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }

    private fun getArticles(query: String) {
        viewModelScope.launch {
            getArticlePaging.invoke(query).cachedIn(viewModelScope).collectLatest { data ->
                _articles.update{
                    data
                }
            }
        }
    }

}