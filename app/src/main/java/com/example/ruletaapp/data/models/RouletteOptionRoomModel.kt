package com.example.ruletaapp.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "roulette-option",
    foreignKeys = [
        ForeignKey(
            entity = RouletteRoomModel::class,
            parentColumns = ["id"],
            childColumns = ["idRoulette"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RouletteOptionRoomModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nameOption: String,
    val idRoulette: Int
)