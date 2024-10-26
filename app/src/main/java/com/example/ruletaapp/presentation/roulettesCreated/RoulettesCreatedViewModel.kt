package com.example.ruletaapp.presentation.roulettesCreated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ruletaapp.data.repositories.RoomRepository
import com.example.ruletaapp.presentation.models.RouletteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    private val _isLoading = MutableStateFlow(false)
    var isLoading:StateFlow<Boolean> = _isLoading

    private val _message = MutableStateFlow("")
    var message: StateFlow<String> = _message

    fun deleteRoulette(rouletteModel: RouletteModel){
        _isLoading.update { true }
        viewModelScope.launch {
            try {
                val response = roomRepository.deleteRoulette(rouletteModel)

                _message.update { response }
            } catch (e:Exception){
                _message.update { "Error: ${e.message}" }
            }
        }
    }

    fun restartState(){
        _message.update { "" }
        _isLoading.update { false }
    }

}