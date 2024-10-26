package com.example.ruletaapp.presentation.roulettesCreated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ruletaapp.data.repositories.RoomRepository
import com.example.ruletaapp.presentation.models.RouletteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RoulettesCreatedViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {

    val roulettesState: StateFlow<List<RouletteModel>> = roomRepository.getRoulettes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList())

}