package com.yusufarisoy.lazygridapp.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.yusufarisoy.lazygrid.data.ItemPlacementType
import com.yusufarisoy.lazygridapp.util.toGridType
import com.yusufarisoy.lazygridapp.view.characters.CharactersScreen
import com.yusufarisoy.lazygridapp.view.characters.CharactersViewModel
import com.yusufarisoy.lazygridapp.view.home.HomeScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationGraph.Home.route
    ) {
        composable(route = NavigationGraph.Home.route) {
            HomeScreen(navController)
        }

        composable(
            route = NavigationGraph.FillSize.routeWithArgument,
            arguments = listOf(
                navArgument(NavigationGraph.FillSize.gridType) { type = NavType.StringType }
            )
        ) {
            val gridType = it.arguments?.getString(NavigationGraph.FillSize.gridType).toGridType()
            val viewModel: CharactersViewModel = hiltViewModel()
            viewModel.fetchCharacters(elementPerRow = 3, constantRowCount = 4, gridType)

            CharactersScreen(
                viewModel,
                navController,
                gridType,
                ItemPlacementType.FillSize,
                NavigationGraph.FillSize.name
            )
        }

        composable(
            route = NavigationGraph.FixedSize.routeWithArgument,
            arguments = listOf(
                navArgument(NavigationGraph.FixedSize.gridType) { type = NavType.StringType }
            )
        ) {
            val gridType = it.arguments?.getString(NavigationGraph.FixedSize.gridType).toGridType()
            val viewModel: CharactersViewModel = hiltViewModel()
            viewModel.fetchCharacters(elementPerRow = 4, constantRowCount = 3, gridType)

            CharactersScreen(
                viewModel,
                navController,
                gridType,
                ItemPlacementType.FixedSize(80.dp),
                NavigationGraph.FixedSize.name
            )
        }

        composable(
            route = NavigationGraph.SpacedBy.routeWithArgument,
            arguments = listOf(
                navArgument(NavigationGraph.SpacedBy.gridType) { type = NavType.StringType }
            )
        ) {
            val gridType = it.arguments?.getString(NavigationGraph.SpacedBy.gridType).toGridType()
            val viewModel: CharactersViewModel = hiltViewModel()
            viewModel.fetchCharacters(elementPerRow = 2, constantRowCount = 5, gridType)

            CharactersScreen(
                viewModel,
                navController,
                gridType,
                ItemPlacementType.SpacedBy(16.dp),
                NavigationGraph.SpacedBy.name
            )
        }
    }
}

sealed class NavigationGraph(val route: String, val name: String) {

    object Home : NavigationGraph("home", "Home")

    object FillSize : NavigationGraph("fillSize", "FillSize") {
        const val routeWithArgument = "fillSize/{gridType}"
        const val gridType = "gridType"
    }

    object FixedSize : NavigationGraph("fixedSize", "FixedSize") {
        const val routeWithArgument = "fixedSize/{gridType}"
        const val gridType = "gridType"
    }

    object SpacedBy : NavigationGraph("spacedBy", "SpacedBy") {
        const val routeWithArgument = "spacedBy/{gridType}"
        const val gridType = "gridType"
    }
}
