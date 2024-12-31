package com.learn.mynewsapp.presentation.navigation.news_navigator

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.learn.mynewsapp.R
import com.learn.mynewsapp.domain.model.Article
import com.learn.mynewsapp.presentation.bookmark.BookmarkScreen
import com.learn.mynewsapp.presentation.bookmark.BookmarkScreenViewModel
import com.learn.mynewsapp.presentation.details.DetailsScreen
import com.learn.mynewsapp.presentation.details.DetailsScreenViewModel
import com.learn.mynewsapp.presentation.home.HomeScreen
import com.learn.mynewsapp.presentation.home.HomeViewModel
import com.learn.mynewsapp.presentation.navigation.Route
import com.learn.mynewsapp.presentation.navigation.news_navigator.components.BottomNavigationItem
import com.learn.mynewsapp.presentation.navigation.news_navigator.components.NewsBottomNavigation
import com.learn.mynewsapp.presentation.search.SearchScreen
import com.learn.mynewsapp.presentation.search.SearchScreenViewModel

@Composable
fun NewsNavigator(

) {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        )
    }

    val navController = rememberNavController()

    val backStackState = navController.currentBackStackEntryAsState().value

    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> {
            Log.d("selectedItem", "${backStackState?.destination?.route}")
            0
        }

        Route.SearchScreen.route ->{
            Log.d("selectedItem", "${backStackState?.destination?.route}")
            1
        }
        Route.BookmarkScreen.route -> {
            Log.d("selectedItem", "${backStackState?.destination?.route}")
            2
        }
        else -> 0
    }

    val isBottomBarVisible =
        listOf(
            Route.HomeScreen.route,
            Route.BookmarkScreen.route,
            Route.SearchScreen.route
        ).any {
            it == backStackState?.destination?.route
        }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selectedItem = selectedItem,
                    onItemClick = { index ->
                        val route = when (index) {
                            0 -> Route.HomeScreen.route
                            1 -> Route.SearchScreen.route
                            2 -> Route.BookmarkScreen.route
                            else -> Route.HomeScreen.route
                        }
                        Log.d("navigator", "route: $route")
                        navigateToTab(navController, route)
                    }
                )
            }
        }
    ) {


        val bottomPadding = it.calculateBottomPadding()


        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {


            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(navController, Route.SearchScreen.route)
                    },
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }

                )
            }


            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchScreenViewModel = hiltViewModel()
                SearchScreen(
                    event = viewModel::onEvent,
                    searchState = viewModel.searchState,
                    navigateToDetail = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }


            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsScreenViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailsScreen(
                            article = article,
                            viewModel = viewModel,
                            onEvent = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
            }


            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkScreenViewModel = hiltViewModel()
                BookmarkScreen(
                    state = viewModel.state,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }


        }
    }
}


private fun navigateToTab(
    navController: NavController,
    route: String
) {
    navController.navigate(route) {
        Log.d("navigateToTab fun", "after calling navigate(route)")
        navController.graph.startDestinationRoute?.let { homeScreenRoute ->  // bcz startDestination is HomeScreen parameter can have any name
            popUpTo(homeScreenRoute) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}


private fun navigateToDetails(
    navController: NavController,
    article: Article
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(Route.DetailsScreen.route)
}