package com.yusufarisoy.lazygridapp.view.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yusufarisoy.lazygridapp.R
import com.yusufarisoy.lazygridapp.ui.theme.*
import com.yusufarisoy.lazygridapp.view.NavigationGraph
import com.yusufarisoy.lazygridapp.view.home.GridType

@Composable
fun CollapseButton(isExpanded: Boolean, onExpandClicked: () -> Unit) {
    val (text, icon) = if (isExpanded) {
        stringResource(R.string.btn_show_less) to Icons.Filled.KeyboardArrowUp
    } else {
        stringResource(R.string.btn_show_more) to Icons.Filled.KeyboardArrowDown
    }
    Spacer(Modifier.height(16.dp))
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onExpandClicked() }
    ) {
        Icon(icon, contentDescription = text, tint = Red700)
        Spacer(Modifier.width(4.dp))
        Text(text)
    }
    Spacer(Modifier.height(16.dp))
}

@Composable
fun BackButton(onBackClicked: () -> Unit) {
    IconButton(onClick = onBackClicked) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription =  stringResource(R.string.btn_back),
            tint = Black
        )
    }
}

@Composable
fun HomeButton(destination: NavigationGraph, gridType: GridType, onClicked: (String) -> Unit) {
    val background = if (gridType == GridType.Normal) Blue700 else Red900
    Button(
        onClick = {
            onClicked("${destination.route}/${gridType.name}")
        },
        modifier = Modifier.width(150.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = background,
            contentColor = White
        )
    ) {
        Text(text = destination.name)
    }
}
