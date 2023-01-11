package com.yusufarisoy.lazygridapp.view.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufarisoy.lazygridapp.data.repository.CharactersRepository
import com.yusufarisoy.lazygridapp.view.home.GridType
import com.yusufarisoy.lazygridapp.view.state.CharactersState
import com.yusufarisoy.lazygridapp.view.state.CharactersState.Loading
import com.yusufarisoy.lazygridapp.view.state.CharactersState.Content
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private var page = 1
    private var isLoading = false
    private var constantRowCount: Int = 3
    var elementPerRow = 3
        private set

    private val _state: MutableStateFlow<CharactersState> = MutableStateFlow(Loading)
    val state: StateFlow<CharactersState>
        get() = _state

    fun fetchCharacters(
        elementPerRow: Int,
        constantRowCount: Int,
        gridType: GridType
    ) = viewModelScope.launch {
        isLoading = true
        this@CharactersViewModel.elementPerRow = elementPerRow
        this@CharactersViewModel.constantRowCount = constantRowCount
        val characters = charactersRepository.fetchCharacters(
            elementPerRow = elementPerRow,
            constantRowCount = constantRowCount,
            isCollapsible = gridType == GridType.Collapsible,
            currentItems = emptyList(),
            currentCollapsibleItems = emptyList(),
            characterName = null,
            page = null
        )
        _state.value = Content(characters)
        isLoading = false
    }

    fun loadMoreCharacters(gridType: GridType) = viewModelScope.launch {
        if (isLoading.not() && _state.value is Content) {
            page++
            isLoading = true
            val uiModel = (_state.value as Content).uiModel
            val characters = charactersRepository.fetchCharacters(
                elementPerRow = elementPerRow,
                constantRowCount = constantRowCount,
                isCollapsible = gridType == GridType.Collapsible,
                currentItems = uiModel.characters,
                currentCollapsibleItems = uiModel.collapsibleCharacters,
                characterName = null,
                page = page
            )
            _state.value = Content(characters)
            isLoading = false
        }
    }
}
