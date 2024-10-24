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
    fun getAllRoulette(): List<RouletteRoomModel>

    @Insert
    fun insertRoulette(roulette:RouletteRoomModel)

    @Update
    fun updateRoulette(roulette:RouletteRoomModel)

    @Delete
    fun deleteRoulette(roulette:RouletteRoomModel)
}