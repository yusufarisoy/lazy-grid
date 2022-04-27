package com.yusufarisoy.lazygridapp.util

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

fun LazyListScope.space(height: Dp) {
    item {
        Spacer(Modifier
            .height(height)
            .fillMaxWidth()
        )
    }
}

fun LazyListState.isScrolledToBottom() : Boolean {
    val lastItem = layoutInfo.visibleItemsInfo.lastOrNull()
    return lastItem != null && lastItem.size + lastItem.offset <= layoutInfo.viewportEndOffset
}
