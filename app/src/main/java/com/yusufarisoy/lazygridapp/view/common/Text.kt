package com.yusufarisoy.lazygridapp.view.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.yusufarisoy.lazygridapp.ui.theme.Black
import com.yusufarisoy.lazygridapp.ui.theme.Gray900

@Composable
fun PrimaryText(text: String) {
    Text(
        text = text,
        color = Black,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun SecondaryText(text: String) {
    Text(
        text = text,
        color = Gray900,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun InfoText(text: String) {
    Text(
        text = text,
        color = Gray900,
        fontSize = 13.sp,
        maxLines = 1,
        overflow = TextOverflow.Clip
    )
}
