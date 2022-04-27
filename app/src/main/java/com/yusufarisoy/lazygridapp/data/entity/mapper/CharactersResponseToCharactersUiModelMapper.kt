package com.yusufarisoy.lazygridapp.data.entity.mapper

import com.yusufarisoy.lazygrid.data.RowItem
import com.yusufarisoy.lazygrid.util.mapToLazyCollapsibleGrid
import com.yusufarisoy.lazygridapp.data.entity.BaseResponse
import com.yusufarisoy.lazygridapp.data.entity.Character
import com.yusufarisoy.lazygridapp.data.entity.CharacterUiModel
import com.yusufarisoy.lazygridapp.util.Mapper
import com.yusufarisoy.lazygridapp.view.state.CharactersUiModel
import javax.inject.Inject

class CharactersResponseToCharactersUiModelMapper @Inject constructor() :
    Mapper<CharactersResponseToCharactersUiModelMapper.Input, CharactersUiModel> {

    override fun map(input: Input): CharactersUiModel = with(input) {
        val charactersResponse = response.data.map {
            CharacterUiModel(
                it.id,
                it.name,
                it.status,
                it.species,
                it.gender,
                it.origin,
                it.image
            )
        }
        val characters = currentItems.flatMap { it.items }.toMutableList()
        characters.addAll(currentCollapsibleItems.flatMap { it.items })
        characters.addAll(charactersResponse)

        val (items, collapsibleItems) = characters
            .mapToLazyCollapsibleGrid(isCollapsible, elementPerRow, constantRowCount)

        return CharactersUiModel(
            response.info.count,
            items,
            collapsibleItems,
            response.info.next
        )
    }

    data class Input(
        val elementPerRow: Int,
        val constantRowCount: Int,
        val isCollapsible: Boolean,
        val currentItems: List<RowItem<CharacterUiModel>>,
        val currentCollapsibleItems: List<RowItem<CharacterUiModel>>,
        val response: BaseResponse<List<Character>>
    )
}
