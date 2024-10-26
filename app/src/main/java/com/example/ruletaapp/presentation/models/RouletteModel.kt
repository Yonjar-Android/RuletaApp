package com.example.ruletaapp.presentation.models

data class RouletteModel(
    val id: Int = 0,
    val rouletteName:String,
    val options: List<RouletteOption>
)

data class RouletteOption(
    val id: Int = 0,
    val rouletteOption:String,
    val rouletteId: Int = 0
)