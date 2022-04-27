package com.yusufarisoy.lazygridapp.network

import com.yusufarisoy.lazygridapp.data.entity.BaseResponse
import com.yusufarisoy.lazygridapp.data.entity.Character
import com.yusufarisoy.lazygridapp.network.EndPoints.GET_CHARACTERS
import com.yusufarisoy.lazygridapp.network.EndPoints.GET_CHARACTER_BY_ID
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(GET_CHARACTERS)
    suspend fun getCharacters(
        @Query("name") characterName: String?,
        @Query("page") page: Int?
    ): BaseResponse<List<Character>>

    @GET(GET_CHARACTER_BY_ID)
    suspend fun getCharacterById(@Path("id") id: Int): Character
}

object EndPoints {
    const val BASE_URL = "https://rickandmortyapi.com/api/"
    const val GET_CHARACTERS = "character"
    const val GET_CHARACTER_BY_ID = "character/{id}"
}
