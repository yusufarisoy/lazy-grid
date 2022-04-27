package com.yusufarisoy.lazygrid

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
 * @param elementPerRow Shouldn't be smaller than 1. If it's equal to 1 then itemPlacementType
 * must be FillSize.
 * @param content Modifier parameter must be used in custom composable content for to correct
 * composition.
 * @since There is a division logic with elementPerRow, any row shouldn't contain more elements
 * than it.
 * **/
inline fun <T> LazyListScope.LazyGrid(
    rows: List<RowItem<T>>,
    elementPerRow: Int,
    itemPlacementType: ItemPlacementType,
    contentPadding: PaddingValues,
    crossinline content: @Composable LazyItemScope.(T, Modifier) -> Unit
) {
    items(rows) { row ->
        GridRow(row, elementPerRow, itemPlacementType, contentPadding, content)
    }
}
