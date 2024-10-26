package com.example.ruletaapp.presentation.createRoulette

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ruletaapp.data.repositories.RoomRepository
import com.example.ruletaapp.presentation.models.RouletteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateRouletteViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    var isLoading:StateFlow<Boolean> = _isLoading

    private val _message = MutableStateFlow("")
    var message: StateFlow<String> = _message

    fun createRoulette(rouletteModel: RouletteModel) {
        _isLoading.update { true }
        viewModelScope.launch {
            try {
                val response = roomRepository.createRoulette(rouletteModel)
                 _message.update { response }
            } catch (e: Exception) {
                _message.update {
                    "Error: ${e.message}"
                }
            }
        }
    }

    fun restartState(){
        _message.update { "" }
        _isLoading.update { false }
    }
}