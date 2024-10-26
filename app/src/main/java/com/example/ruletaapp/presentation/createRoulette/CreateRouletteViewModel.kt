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

    private val _isLoading = MutableStateFlow<Boolean>(false)
    var isLoading:StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String>("")
    var error: StateFlow<String> = _error

    init {
        viewModelScope.launch {
            val response = roomRepository.getRoulettes()

            println(response)
        }
    }

    fun createRoulette(rouletteModel: RouletteModel) {
        viewModelScope.launch {
            try {
                roomRepository.createRoulette(rouletteModel)
            } catch (e: Exception) {
                _error.update {
                    "Error: ${e.message}"
                }
            }
        }
    }
}