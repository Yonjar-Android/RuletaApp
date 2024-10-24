package com.example.ruletaapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ruletaapp.data.database.dao.RouletteDao
import com.example.ruletaapp.data.database.dao.RouletteOptionDao
import com.example.ruletaapp.data.models.RouletteOptionRoomModel
import com.example.ruletaapp.data.models.RouletteRoomModel

@Database(
    entities = [RouletteRoomModel::class, RouletteOptionRoomModel::class],
    version = 1,
    exportSchema = false
)
abstract class RouletteDatabase : RoomDatabase() {

    abstract fun rouletteDao(): RouletteDao
    abstract fun rouletteOptionDao(): RouletteOptionDao
}