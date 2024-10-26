package com.example.ruletaapp.data.repositories

import com.example.ruletaapp.data.database.dao.RouletteDao
import com.example.ruletaapp.data.database.dao.RouletteOptionDao
import com.example.ruletaapp.data.models.RouletteOptionRoomModel
import com.example.ruletaapp.presentation.models.RouletteModel
import com.example.ruletaapp.utils.toOptionModel
import com.example.ruletaapp.utils.toRoomModel
import com.example.ruletaapp.utils.toRouletteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
        } catch (e: Exception) {
            return "Error: ${e.message}"
        }
    }

    fun getRoulettes(): Flow<List<RouletteModel>> {

        val response = rouletteDao.getAllRoulette()

        return response.map { it ->
            it.map {
                it.toRouletteModel(listOf())
            }
        }
    }

    suspend fun getRouletteById(rouletteId: Int):RouletteModel{
        try {
            val response = rouletteDao.getRouletteById(rouletteId)

            val options = optionDao.getAllFromRoulette(response.id)

            val roulette = response.toRouletteModel(options = options.map {
                it.toOptionModel()
            })

            return roulette
        } catch (e:Exception){
            return RouletteModel(rouletteName = "", options = listOf())
        }
    }

    suspend fun deleteRoulette(roulette: RouletteModel):String{
        try {
            rouletteDao.deleteRoulette(roulette.toRoomModel())
            return "Se ha eliminado la ruleta exitosamente"
        } catch (e: Exception){
            return "Error: ${e.message}"
        }
    }
}