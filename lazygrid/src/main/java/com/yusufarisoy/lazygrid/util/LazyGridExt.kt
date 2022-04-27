package com.yusufarisoy.lazygrid.util

import com.yusufarisoy.lazygrid.data.RowItem

fun <T> List<T>.mapToLazyGrid(elementPerRow: Int) = this
    .chunked(elementPerRow)
    .map { items ->
        RowItem(items)
    }

/***
 * @param isCollapsible For to control grid's collapsibility with a feature flag.
 * ***/
fun <T> List<T>.mapToLazyCollapsibleGrid(
    isCollapsible: Boolean,
    elementPerRow: Int,
    constantRowCount: Int
): Pair<List<RowItem<T>>, List<RowItem<T>>> {
    val rowItems = this
        .chunked(elementPerRow)
        .map { items ->
            RowItem(items)
        }
    val rows = if (isCollapsible && rowItems.size > constantRowCount) {
        rowItems.take(constantRowCount) to rowItems.drop(constantRowCount)
    } else {
        rowItems to emptyList()
    }

    return rows
}
