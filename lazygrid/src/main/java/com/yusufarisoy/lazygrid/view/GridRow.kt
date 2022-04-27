package com.yusufarisoy.lazygrid.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yusufarisoy.lazygrid.data.ItemPlacementType
import com.yusufarisoy.lazygrid.data.ItemPlacementType.FillSize
import com.yusufarisoy.lazygrid.data.ItemPlacementType.FixedSize
import com.yusufarisoy.lazygrid.data.ItemPlacementType.SpacedBy
import com.yusufarisoy.lazygrid.data.RowItem

/**
 * @see ItemPlacementType
 * @param row Shouldn't have more items than elementPerRow, this case can cause wrong item
 * placements or even exceptions.
 * @param content Modifier parameter must be used in custom composable content for to correct
 * composition.
 * @param elementPerRow Shouldn't be smaller than 1. If it's equal to 1 then itemPlacementType
 * must be FillSize.
 * **/
@Composable
inline fun <T> LazyItemScope.GridRow(
    row: RowItem<T>,
    elementPerRow: Int,
    placementType: ItemPlacementType,
    contentPadding: PaddingValues,
    crossinline content: @Composable LazyItemScope.(T, Modifier) -> Unit
) = when (placementType) {
    is FillSize -> {
        Row(modifier = Modifier.fillMaxWidth().padding(contentPadding)) {
            row.items.forEachIndexed { index, item ->
                content(item, Modifier.fillMaxWidth(fraction = 1f / (elementPerRow - index)))
            }
        }
    }
    is FixedSize -> {
        BoxWithConstraints(modifier = Modifier.fillMaxWidth().padding(contentPadding)) {
            val totalSpace = maxWidth - (placementType.itemWidth * elementPerRow)
            val space = totalSpace / (elementPerRow - 1)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(space)
            ) {
                row.items.forEach { item ->
                    content(item, Modifier.width(placementType.itemWidth))
                }
            }
        }
    }
    is SpacedBy -> {
        BoxWithConstraints(modifier = Modifier.fillMaxWidth().padding(contentPadding)) {
            val spaceCount = elementPerRow - 1
            val totalWidth = maxWidth - (placementType.space * spaceCount)
            val itemWidth = totalWidth / elementPerRow
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(placementType.space)
            ) {
                row.items.forEach { item ->
                    content(item, Modifier.width(itemWidth))
                }
            }
        }
    }
}
