package com.kkh.newsapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.kkh.domain.model.ArticleItem

@Composable
fun HomeScreen(
    onArticleClicked: (ArticleItem) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    val articles = viewModel.articles.collectAsLazyPagingItems()

    LaunchedEffect(key1 = articles.loadState) {
        if (articles.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                (articles.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Scaffold(
        topBar = {
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                TextField(
                    value = searchText,
                    onValueChange = viewModel::onSearchTextChanged,
                    placeholder = {
                        Text(text = "Search")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (articles.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(
                        count = articles.itemCount,
                        key = articles.itemKey {
                            it.url
                        }
                    ) {
                        val item = articles[it]
                        item?.let { article ->
                            ArticleItem(articleItem = article) {
                                onArticleClicked(item)
                            }
                        }
                    }
                    item {
                        if (articles.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}