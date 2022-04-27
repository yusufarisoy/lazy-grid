package com.yusufarisoy.lazygridapp.view.characters

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yusufarisoy.lazygrid.LazyCollapsibleGrid
import com.yusufarisoy.lazygrid.LazyGrid
import com.yusufarisoy.lazygrid.data.ItemPlacementType
import com.yusufarisoy.lazygridapp.data.entity.CharacterUiModel
import com.yusufarisoy.lazygridapp.util.isScrolledToBottom
import com.yusufarisoy.lazygridapp.util.space
import com.yusufarisoy.lazygridapp.view.characters.item.FillSizeItem
import com.yusufarisoy.lazygridapp.view.characters.item.FixedSizeItem
import com.yusufarisoy.lazygridapp.view.characters.item.SpacedByItem
import com.yusufarisoy.lazygridapp.view.common.BackButton
import com.yusufarisoy.lazygridapp.view.common.CollapseButton
import com.yusufarisoy.lazygridapp.view.common.LoadingLayout
import com.yusufarisoy.lazygridapp.view.home.GridType
import com.yusufarisoy.lazygridapp.view.state.CharactersState
import com.yusufarisoy.lazygridapp.view.state.CharactersUiModel

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel,
    navController: NavController,
    gridType: GridType,
    itemPlacementType: ItemPlacementType,
    itemPlacementTypeName: String
) {
    val state = viewModel.state.collectAsState()

    when (state.value) {
        is CharactersState.Loading -> LoadingLayout()
        is CharactersState.Content -> {
            val uiModel = (state.value as CharactersState.Content).uiModel
            PageContent(
                uiModel,
                gridType,
                itemPlacementType,
                itemPlacementTypeName,
                viewModel.elementPerRow,
                loadMoreCharacters = { viewModel.loadMoreCharacters(gridType) },
                onBackClicked = { navController.popBackStack() }
            )
        }
        is CharactersState.Error -> {}
    }
}

@Composable
private fun PageContent(
    uiModel: CharactersUiModel,
    gridType: GridType,
    itemPlacementType: ItemPlacementType,
    itemPlacementTypeName: String,
    elementPerRow: Int,
    loadMoreCharacters: () -> Unit,
    onBackClicked: () -> Unit
) {
    val scrollState = rememberLazyListState()
    var isExpanded by remember { mutableStateOf(false) }
    val contentPadding = if (itemPlacementType is ItemPlacementType.FillSize) {
        PaddingValues()
    } else {
        PaddingValues(horizontal = 16.dp)
    }

    LazyColumn(
        state = scrollState,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            BackButton(onBackClicked)
        }

        item {
            Header(gridType, itemPlacementTypeName)
        }
        space(16.dp)

        item {
            CharactersInfo(uiModel.characterCount)
        }
        space(10.dp)

        if (gridType == GridType.Normal) {
            LazyGrid(
                rows = uiModel.characters,
                elementPerRow = elementPerRow,
                itemPlacementType = itemPlacementType,
                contentPadding = contentPadding
            ) { item, modifier ->
                GridItem(item, modifier, itemPlacementType)
            }
        } else {
            LazyCollapsibleGrid(
                rows = uiModel.characters,
                collapsibleRows = uiModel.collapsibleCharacters,
                elementPerRow = elementPerRow,
                itemPlacementType = itemPlacementType,
                contentPadding = contentPadding,
                isExpanded = isExpanded,
                collapseButton = { CollapseButton(isExpanded) { isExpanded = !isExpanded } }
            ) { item, modifier ->
                GridItem(item, modifier, itemPlacementType)
            }
        }
        space(8.dp)
    }

    if (gridType == GridType.Normal) {
        val reachedToBottom by remember {
            derivedStateOf {
                scrollState.isScrolledToBottom()
            }
        }
        LaunchedEffect(reachedToBottom) { loadMoreCharacters() }
    }
}

@Composable
private fun GridItem(
    character: CharacterUiModel,
    modifier: Modifier,
    itemPlacementType: ItemPlacementType
) {
    when (itemPlacementType) {
        is ItemPlacementType.FillSize -> FillSizeItem(character, modifier)
        is ItemPlacementType.FixedSize -> FixedSizeItem(character, modifier)
        is ItemPlacementType.SpacedBy -> SpacedByItem(character, modifier)
    }
}
