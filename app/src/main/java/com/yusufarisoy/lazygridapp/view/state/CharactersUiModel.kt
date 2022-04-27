package com.yusufarisoy.lazygridapp.view.state

import com.yusufarisoy.lazygrid.data.RowItem
import com.yusufarisoy.lazygridapp.data.entity.CharacterUiModel

data class CharactersUiModel(
    val characterCount: Int,
    val characters: List<RowItem<CharacterUiModel>>,
    val collapsibleCharacters: List<RowItem<CharacterUiModel>> = emptyList(),
    val nextPage: String
)
