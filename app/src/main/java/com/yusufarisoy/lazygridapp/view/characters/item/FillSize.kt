package com.yusufarisoy.lazygridapp.view.characters.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.yusufarisoy.lazygridapp.data.entity.CharacterUiModel

@Composable
fun FillSizeItem(character: CharacterUiModel, modifier: Modifier) = with(character) {
    // Modifier parameter must be used as top view's modifier for correct composition.
    Surface(modifier = modifier) {
        Image(
            rememberImagePainter(image),
            contentDescription = name,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.height(120.dp)
        )
    }
}
