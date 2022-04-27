package com.yusufarisoy.lazygrid.data

import androidx.compose.ui.unit.Dp

/**
 * @property FillSize : All items share the full width without any spaces.
 * @property FixedSize : Items placed with a fixed width and spaced equally according to remaining
 * space.
 * @property SpacedBy : Items spaced by a fixed space and shared the remaining width.
 * **/
sealed class ItemPlacementType {

    object FillSize : ItemPlacementType()

    data class FixedSize(val itemWidth: Dp) : ItemPlacementType()

    data class SpacedBy(val space: Dp) : ItemPlacementType()
}
