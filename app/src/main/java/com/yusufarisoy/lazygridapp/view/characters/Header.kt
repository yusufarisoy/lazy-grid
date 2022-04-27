package com.yusufarisoy.lazygridapp.view.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yusufarisoy.lazygridapp.R
import com.yusufarisoy.lazygridapp.view.common.PrimaryText
import com.yusufarisoy.lazygridapp.view.common.SecondaryText
import com.yusufarisoy.lazygridapp.view.home.GridType

@Composable
fun Header(gridType: GridType, itemPlacementTypeName: String) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        val text = if (gridType == GridType.Normal) {
            stringResource(R.string.text_lazy_grid)
        } else {
            stringResource(R.string.text_lazy_collapsible_grid)
        }
        PrimaryText(text)
        Spacer(Modifier.height(4.dp))
        SecondaryText(
            text = stringResource(
                id = R.string.text_item_placement_type, itemPlacementTypeName
            )
        )
    }
}
