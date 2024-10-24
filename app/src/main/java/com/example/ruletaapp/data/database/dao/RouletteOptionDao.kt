package com.example.ruletaapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ruletaapp.data.models.RouletteOptionRoomModel

@Dao
interface RouletteOptionDao {
    @Query("Select * from `roulette-option` Where idRoulette= :idRoulette")
    fun getAllFromRoulette(idRoulette:Int): List<RouletteOptionRoomModel>

    @Insert
    fun insertRouletteOption(rouletteOption:RouletteOptionRoomModel)

    @Update
    fun updateRouletteOption(rouletteOption: RouletteOptionRoomModel)

    @Delete
    fun deleteRouletteOption(rouletteOption: RouletteOptionRoomModel)

}