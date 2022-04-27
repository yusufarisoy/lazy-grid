package com.yusufarisoy.lazygridapp.view.characters.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.yusufarisoy.lazygridapp.R
import com.yusufarisoy.lazygridapp.data.entity.CharacterUiModel
import com.yusufarisoy.lazygridapp.ui.theme.Green
import com.yusufarisoy.lazygridapp.ui.theme.Red700
import com.yusufarisoy.lazygridapp.ui.theme.Gray700
import com.yusufarisoy.lazygridapp.ui.theme.Black

@Composable
fun FixedSizeItem(
    character: CharacterUiModel,
    modifier: Modifier
) = with(character) {
    val color = when(status) {
        "Alive" -> Green
        "Dead" -> Red700
        else -> Gray700
    }
    // Modifier parameter must be used as top view's modifier for correct composition.
    Column(modifier = modifier.padding(bottom = 16.dp)) {
        Surface(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberImagePainter(image),
                contentDescription = name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = name.split(" ").first(),
            color = Black,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 2.dp)
        ) {
            Icon(
                painterResource(R.drawable.ic_circle),
                contentDescription = status,
                tint = color,
                modifier = Modifier.scale(0.5f).padding(top = 3.dp)
            )
            Text(
                text = status,
                color = color,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}
