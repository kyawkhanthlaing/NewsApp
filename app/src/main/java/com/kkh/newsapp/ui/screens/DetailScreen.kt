package com.kkh.newsapp.ui.screens

import android.telecom.Call.Details
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.kkh.domain.model.ArticleItem

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    articleItem: ArticleItem,
    onBackPressed:()->Unit
){
    Column(
       modifier = Modifier
           .fillMaxSize()
           .padding(16.dp)

    ){
        GlideImage(
            model = articleItem.urlToImage,
            contentDescription = "article image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
        Text(text =articleItem.title)
        Text(text = articleItem.author)
        Text(text = articleItem.description)

    }

}