package com.yusufarisoy.lazygridapp.domain

import com.yusufarisoy.lazygrid.data.RowItem
import com.yusufarisoy.lazygridapp.data.entity.CharacterUiModel
import com.yusufarisoy.lazygridapp.data.repository.ApiRepository
import com.yusufarisoy.lazygridapp.util.CoroutineUseCase
import com.yusufarisoy.lazygridapp.domain.FetchCharactersUseCase.Params
import com.yusufarisoy.lazygridapp.view.state.CharactersUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchCharactersUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) : CoroutineUseCase<Params, CharactersUiModel> {

    override suspend fun run(params: Params) = withContext(Dispatchers.IO) {
        apiRepository.fetchCharacters(
            params.elementPerRow,
            params.constantRowCount,
            params.isCollapsible,
            params.currentItems,
            params.currentCollapsibleItems,
            params.characterName,
            params.page
        )
    }

    data class Params(
        val elementPerRow: Int,
        val constantRowCount: Int,
        val isCollapsible: Boolean = false,
        val currentItems: List<RowItem<CharacterUiModel>> = emptyList(),
        val currentCollapsibleItems: List<RowItem<CharacterUiModel>> = emptyList(),
        val characterName: String? = null,
        val page: Int? = null
    )
}
