package com.yusufarisoy.lazygridapp.view.state

import java.lang.Exception

sealed class CharactersState {

    object Loading : CharactersState()

    data class Content(
        val uiModel: CharactersUiModel
    ) : CharactersState()

    data class Error(val exception: Exception) : CharactersState()
}
