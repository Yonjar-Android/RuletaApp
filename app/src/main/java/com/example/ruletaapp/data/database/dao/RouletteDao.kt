package com.example.ruletaapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ruletaapp.data.models.RouletteRoomModel

@Dao
interface RouletteDao {
    @Query("Select * From roulette")
    suspend fun getAllRoulette(): List<RouletteRoomModel>

    @Insert
    suspend fun insertRoulette(roulette:RouletteRoomModel): Long

    @Update
    suspend fun updateRoulette(roulette:RouletteRoomModel)

    @Delete
    suspend fun deleteRoulette(roulette:RouletteRoomModel)
}