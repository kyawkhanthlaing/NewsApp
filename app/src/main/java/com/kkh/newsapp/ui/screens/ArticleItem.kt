package com.kkh.newsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.Placeholder
import com.kkh.domain.model.ArticleItem

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArticleItem(
    modifier: Modifier = Modifier,
    articleItem: ArticleItem,
    onClick: (ArticleItem) -> Unit
) {

    Box(
        modifier = modifier
            .clickable {
                onClick(articleItem)
            }
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column {
            GlideImage(
                modifier = modifier
                    .fillMaxWidth()
                    .height(100.dp),
                model = articleItem.urlToImage,
                contentDescription = "article image",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
            Text(
                text = articleItem.title,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}