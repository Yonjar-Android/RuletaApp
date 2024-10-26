package com.example.ruletaapp.data.repositories

import com.example.ruletaapp.data.database.dao.RouletteDao
import com.example.ruletaapp.data.database.dao.RouletteOptionDao
import com.example.ruletaapp.data.models.RouletteOptionRoomModel
import com.example.ruletaapp.presentation.models.RouletteModel
import com.example.ruletaapp.utils.toRoomModel
import com.example.ruletaapp.utils.toRoomOptionModel
import com.example.ruletaapp.utils.toRouletteModel
import javax.inject.Inject


class RoomRepository @Inject constructor(
    private val rouletteDao: RouletteDao,
    private val optionDao: RouletteOptionDao
) {
    suspend fun createRoulette(roulette: RouletteModel): String {
        try {
            val rouletteId = rouletteDao.insertRoulette(roulette.toRoomModel())

            roulette.options.forEach { option ->
                optionDao.insertRouletteOption(
                    RouletteOptionRoomModel(
                        nameOption = option.rouletteOption,
                        idRoulette = rouletteId.toInt()
                    )
                )
            }

            return "La ruleta se ha creado con éxito"
        } catch (e:Exception){
            return "Error: ${e.message}"
        }

    }

    suspend fun getRoulettes(): List<RouletteModel> {
        val roulettes = mutableListOf<RouletteModel>()

        val response = rouletteDao.getAllRoulette()
        for (i in response.indices) {
            roulettes.add(response[i].toRouletteModel(listOf()))
        }
        return roulettes
    }
}