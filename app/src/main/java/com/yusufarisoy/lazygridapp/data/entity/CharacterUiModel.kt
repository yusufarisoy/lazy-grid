package com.yusufarisoy.lazygridapp.data.entity

data class CharacterUiModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: Origin,
    val image: String,
)
