package com.example.ruletaapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roulette")
data class RouletteRoomModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)