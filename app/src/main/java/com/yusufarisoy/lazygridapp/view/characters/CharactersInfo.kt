package com.yusufarisoy.lazygridapp.view.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yusufarisoy.lazygridapp.R
import com.yusufarisoy.lazygridapp.view.common.InfoText
import com.yusufarisoy.lazygridapp.view.common.PrimaryText

@Composable
fun CharactersInfo(characterCount: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        PrimaryText(text = stringResource(R.string.text_characters))
        InfoText(
            text = stringResource(id = R.string.text_total, characterCount)
        )
    }
}
