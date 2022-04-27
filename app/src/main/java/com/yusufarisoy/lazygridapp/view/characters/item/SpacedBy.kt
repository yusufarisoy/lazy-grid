package com.yusufarisoy.lazygridapp.view.characters.item

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.yusufarisoy.lazygridapp.R
import com.yusufarisoy.lazygridapp.data.entity.CharacterUiModel
import com.yusufarisoy.lazygridapp.ui.theme.Black
import com.yusufarisoy.lazygridapp.ui.theme.Gray700
import com.yusufarisoy.lazygridapp.ui.theme.Green
import com.yusufarisoy.lazygridapp.ui.theme.Red700

@Composable
fun SpacedByItem(
    character: CharacterUiModel,
    modifier: Modifier
) = with(character) {
    var isExpanded by remember { mutableStateOf(false) }
    val color = when(status) {
    "Alive" -> Green
    "Dead" -> Red700
    else -> Gray700
    }
    val icon = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    // Modifier parameter must be used as top view's modifier for to correct composition.
    Surface(
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.padding(bottom = 16.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                rememberImagePainter(image),
                contentDescription = name,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = name,
                color = Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            )
            Spacer(Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
            ) {
                Icon(
                    painterResource(R.drawable.ic_circle),
                    contentDescription = name,
                    tint = color,
                    modifier = Modifier
                        .scale(0.6f)
                        .padding(top = 3.dp)
                )
                Text(
                    text = status,
                    color = color,
                    fontSize = 13.sp,
                )
            }
            Text(
                text = species,
                fontSize = 13.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                    val genderText = buildAnnotatedString {
                        append(stringResource(id = R.string.text_gender))
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(" $gender")
                        }
                    }
                    Text(
                        text = genderText,
                        fontSize = 13.sp,
                        modifier = Modifier.fillMaxWidth()
                    )

                    val originText = buildAnnotatedString {
                        append(stringResource(id = R.string.text_origin))
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(" ${origin.name}")
                        }
                    }
                    Text(
                        text = originText,
                        fontSize = 13.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            IconButton(
                onClick = { isExpanded = !isExpanded },
                modifier = Modifier
                    .scale(0.8f)
                    .size(36.dp)
            ) { Icon(icon, contentDescription = null) }
        }
    }
}
