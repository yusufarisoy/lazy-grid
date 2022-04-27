package com.yusufarisoy.lazygridapp.view.common

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yusufarisoy.lazygrid.LazyGrid
import com.yusufarisoy.lazygrid.data.ItemPlacementType
import com.yusufarisoy.lazygrid.data.RowItem
import com.yusufarisoy.lazygridapp.ui.theme.Gray700
import com.yusufarisoy.lazygridapp.util.space

@Composable
fun LoadingLayout() {
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 900f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1300,
                easing = FastOutLinearInEasing
            )
        )
    )

    val gradient = listOf(
        Gray700.copy(alpha = 0.9f),
        Gray700.copy(alpha = 0.5f),
        Gray700.copy(alpha = 0.9f)
    )
    val headerBrush = linearGradient(
        colors = gradient,
        start = Offset(300f, 300f),
        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
    )
    val brush = linearGradient(
        colors = gradient,
        start = Offset(100f, 100f),
        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
    )
    val rows = mutableListOf<RowItem<String>>()
    for (i in 0..3) {
        rows.add(RowItem(listOf("", "")))
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        item {
            ShimmerItem(4.dp, 8.dp, 1f, 100.dp, headerBrush)
        }
        space(32.dp)

        LazyGrid(
            rows = rows,
            elementPerRow = 2,
            itemPlacementType = ItemPlacementType.SpacedBy(16.dp),
            contentPadding = PaddingValues()
        ) { _, modifier ->
            Row(modifier = modifier.padding(bottom = 16.dp)) {
                ShimmerItem(2.dp, 4.dp, .35f, 60.dp, brush)
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 8.dp)
                ) {
                    ShimmerItem(2.dp, 4.dp, .6f, 10.dp, brush)
                    Spacer(Modifier.height(4.dp))
                    ShimmerItem(2.dp, 4.dp, .8f, 10.dp, brush)
                    Spacer(Modifier.height(4.dp))
                    ShimmerItem(2.dp, 4.dp, 1f, 10.dp, brush)
                }
            }
        }
        space(16.dp)

        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                ShimmerItem(4.dp, 8.dp, .5f, 60.dp, headerBrush)
                Spacer(Modifier.width(16.dp))
                ShimmerItem(4.dp, 8.dp, 1f, 60.dp, headerBrush)
            }
        }
    }
}

@Composable
private fun ShimmerItem(elevation: Dp, radius: Dp, fraction: Float, height: Dp, brush: Brush) {
    Surface(
        elevation = elevation,
        shape = RoundedCornerShape(radius),
        modifier = Modifier.fillMaxWidth(fraction)
    ) {
        Spacer(
            modifier = Modifier
                .height(height)
                .fillMaxWidth()
                .background(brush)
        )
    }
}
