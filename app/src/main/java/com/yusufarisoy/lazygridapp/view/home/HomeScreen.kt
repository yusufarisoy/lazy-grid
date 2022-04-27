package com.yusufarisoy.lazygridapp.view.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yusufarisoy.lazygridapp.R
import com.yusufarisoy.lazygridapp.view.common.HomeButton
import com.yusufarisoy.lazygridapp.view.common.PrimaryText
import com.yusufarisoy.lazygridapp.view.NavigationGraph
import kotlinx.coroutines.delay

enum class GridType {
    Normal,
    Collapsible
}

@Composable
fun HomeScreen(navController: NavController) {
    val destinations = listOf(
        NavigationGraph.FillSize, NavigationGraph.FixedSize, NavigationGraph.SpacedBy
    )
    var isVisible by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(800)
        isVisible = true
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(1f / 2),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PrimaryText(text = stringResource(R.string.title_lazy_grid))
            Spacer(Modifier.height(16.dp))
            destinations.forEach { destination ->
                HomeButton(destination, GridType.Normal) { route ->
                    navController.navigate(route)
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PrimaryText(text = stringResource(R.string.title_lazy_collapsible_grid))
            Spacer(Modifier.height(16.dp))
            HomeButton(NavigationGraph.FillSize, GridType.Collapsible) { route ->
                navController.navigate(route)
            }
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(animationSpec = tween(1000)) +
                        expandVertically(animationSpec = tween(durationMillis = 1000)),
                exit = fadeOut(animationSpec = tween(1000)) +
                        shrinkVertically (animationSpec = tween(durationMillis = 1000,))
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HomeButton(NavigationGraph.FixedSize, GridType.Collapsible) { route ->
                        navController.navigate(route)
                    }
                    HomeButton(NavigationGraph.SpacedBy, GridType.Collapsible) { route ->
                        navController.navigate(route)
                    }
                }
            }
        }
    }
}
