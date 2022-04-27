package com.yusufarisoy.lazygridapp.util

import com.yusufarisoy.lazygridapp.view.home.GridType

fun String?.toGridType(): GridType = if (this == GridType.Collapsible.name) {
    GridType.Collapsible
} else {
    GridType.Normal
}
