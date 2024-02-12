package com.kkh.newsapp.ui.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kkh.domain.model.ArticleItem
import com.kkh.newsapp.ui.screens.DetailScreen
import com.kkh.newsapp.ui.screens.HomeScreen
import com.kkh.newsapp.ui.util.ScreenRoute

@Composable
fun MainNavGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoute.HomeScreen.route) {
        composable(ScreenRoute.HomeScreen.route) {
            HomeScreen(onArticleClicked = { articleItem ->
                navController.currentBackStackEntry?.savedStateHandle?.set("article", articleItem)
                navController.navigate(ScreenRoute.DetailScreen.route)
            })
        }
        composable(ScreenRoute.DetailScreen.route) {
            val movieItem =
                navController.previousBackStackEntry?.savedStateHandle?.get<ArticleItem>("article")
            movieItem?.let {
                DetailScreen(articleItem = it) {
                    navController.popBackStack()
                }
            }
        }
    }
}