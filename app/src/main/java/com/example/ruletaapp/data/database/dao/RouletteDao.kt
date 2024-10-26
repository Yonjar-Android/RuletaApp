package com.example.ruletaapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ruletaapp.data.models.RouletteRoomModel
import kotlinx.coroutines.flow.Flow

@Dao
interface RouletteDao {
    @Query("Select * From roulette")
    fun getAllRoulette(): Flow<List<RouletteRoomModel>>

    @Query("Select * From roulette Where id = :id")
    suspend fun getRouletteById(id:Int): RouletteRoomModel

    @Insert
    suspend fun insertRoulette(roulette:RouletteRoomModel): Long

    @Update
    suspend fun updateRoulette(roulette:RouletteRoomModel)

    @Delete
    suspend fun deleteRoulette(roulette:RouletteRoomModel)
}