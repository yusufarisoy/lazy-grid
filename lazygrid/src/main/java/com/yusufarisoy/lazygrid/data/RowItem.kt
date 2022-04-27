package com.yusufarisoy.lazygrid.data

import androidx.compose.runtime.Stable

/**
 * @see Stable for more information.
 * **/
@Stable
data class RowItem<T>(
    val items: List<T>
)
