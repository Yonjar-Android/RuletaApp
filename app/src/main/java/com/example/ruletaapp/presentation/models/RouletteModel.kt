package com.example.ruletaapp.presentation.models

data class RouletteModel(
    val id: Int,
    val rouletteName:String,
    val options: List<RouletteOption>
)

data class RouletteOption(
    val id: Int,
    val rouletteOption:String,
    val rouletteId: Int
)