package com.yusufarisoy.lazygridapp.data.repository

import com.yusufarisoy.lazygrid.data.RowItem
import com.yusufarisoy.lazygridapp.data.entity.CharacterUiModel
import com.yusufarisoy.lazygridapp.data.entity.mapper.CharactersResponseToCharactersUiModelMapper
import com.yusufarisoy.lazygridapp.network.ApiService
import com.yusufarisoy.lazygridapp.util.mapWith
import com.yusufarisoy.lazygridapp.view.state.CharactersUiModel
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val service: ApiService,
    private val mapper: CharactersResponseToCharactersUiModelMapper
) {

    suspend fun fetchCharacters(
        elementPerRow: Int,
        constantRowCount: Int,
        isCollapsible: Boolean = false,
        currentItems: List<RowItem<CharacterUiModel>>,
        currentCollapsibleItems: List<RowItem<CharacterUiModel>>,
        characterName: String? = null,
        page: Int? = null
    ): CharactersUiModel {
        val response = service.getCharacters(
            characterName,
            page
        )
        val input = CharactersResponseToCharactersUiModelMapper.Input(
            elementPerRow,
            constantRowCount,
            isCollapsible,
            currentItems,
            currentCollapsibleItems,
            response
        )

        return input.mapWith(mapper)
    }
}
