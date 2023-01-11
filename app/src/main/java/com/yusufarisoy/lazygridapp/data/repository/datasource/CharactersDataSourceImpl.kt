package com.yusufarisoy.lazygridapp.data.repository.datasource

import com.yusufarisoy.lazygridapp.data.entity.BaseResponse
import com.yusufarisoy.lazygridapp.data.entity.Character
import com.yusufarisoy.lazygridapp.network.ApiService
import javax.inject.Inject

class CharactersDataSourceImpl @Inject constructor(
    private val service: ApiService
) : CharactersDataSource {

    override suspend fun getCharacters(
        characterName: String?,
        page: Int?
    ): BaseResponse<List<Character>> {
        return service.getCharacters(characterName,  page)
    }

    override suspend fun getCharacterById(id: Int): Character {
        return service.getCharacterById(id)
    }
}
