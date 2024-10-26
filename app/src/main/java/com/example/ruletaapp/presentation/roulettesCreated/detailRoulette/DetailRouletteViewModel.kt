package com.example.ruletaapp.presentation.roulettesCreated.detailRoulette

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
class DetailRouletteViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {

    private val _roulette =
        MutableStateFlow(RouletteModel(rouletteName = "", options = listOf()))

    var roulette:StateFlow<RouletteModel> = _roulette

    private val _message = MutableStateFlow<String>("")
    var message:StateFlow<String> = _message

    fun getRouletteById(rouletteId: Int){
        viewModelScope.launch {
            try {
                val response = roomRepository.getRouletteById(rouletteId)

                if (response.id != 0){
                    _roulette.update { response }
                } else{

                }
            } catch (e:Exception){
                _message.update { "Error: ${e.message}" }
            }
        }
    }

}