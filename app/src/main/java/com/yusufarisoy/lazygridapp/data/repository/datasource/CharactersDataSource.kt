package com.yusufarisoy.lazygridapp.data.repository.datasource

import com.yusufarisoy.lazygridapp.data.entity.BaseResponse
import com.yusufarisoy.lazygridapp.data.entity.Character

interface CharactersDataSource {

    suspend fun getCharacters(
        characterName: String?,
        page: Int?
    ): BaseResponse<List<Character>>

    suspend fun getCharacterById(id: Int): Character
}
