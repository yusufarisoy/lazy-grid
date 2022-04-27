package com.yusufarisoy.lazygrid

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yusufarisoy.lazygrid.data.ItemPlacementType
import com.yusufarisoy.lazygrid.data.RowItem
import com.yusufarisoy.lazygrid.view.GridRow

/**
 * @see RowItem
 * @see GridRow
 * @see ItemPlacementType
 * @param rows Constantly shown RowItems.
 * @param collapsibleRows Rows which will be collapsed with the change of isExpanded field.
 * @param isExpanded Handles the collapsing functionality, should be a mutableState to control
 * collapsibleRows.
 * @param elementPerRow Shouldn't be smaller than 1. If it's equal to 1 then itemPlacementType
 * must be FillSize.
 * @param collapseButton Should control the isExpanded value with its onClick.
 * @param content Modifier parameter must be used in custom composable content for to correct
 * composition.
 * @since There is a division logic with elementPerRow, any row shouldn't contain more elements
 * than it.
 * **/
inline fun <T> LazyListScope.LazyCollapsibleGrid(
    rows: List<RowItem<T>>,
    collapsibleRows: List<RowItem<T>>,
    elementPerRow: Int,
    itemPlacementType: ItemPlacementType,
    contentPadding: PaddingValues,
    isExpanded: Boolean,
    enterTransition: EnterTransition = expandVertically(),
    exitTransition: ExitTransition = shrinkVertically(),
    crossinline collapseButton: @Composable () -> Unit,
    crossinline content: @Composable LazyItemScope.(T, Modifier) -> Unit
) {
    items(rows) { row ->
        GridRow(row, elementPerRow, itemPlacementType, contentPadding, content)
    }

    if (collapsibleRows.isNotEmpty()) {
        items(collapsibleRows) { row ->
            AnimatedVisibility(
                visible = isExpanded,
                enter = enterTransition,
                exit = exitTransition
            ) {
                GridRow(row, elementPerRow, itemPlacementType, contentPadding, content)
            }
        }
        item { collapseButton() }
    }
}
