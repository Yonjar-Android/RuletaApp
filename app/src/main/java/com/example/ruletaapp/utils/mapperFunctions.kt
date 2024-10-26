package com.example.ruletaapp.utils

import com.example.ruletaapp.data.models.RouletteOptionRoomModel
import com.example.ruletaapp.data.models.RouletteRoomModel
import com.example.ruletaapp.presentation.models.RouletteModel
import com.example.ruletaapp.presentation.models.RouletteOption

fun RouletteModel.toRoomModel(): RouletteRoomModel {
    return RouletteRoomModel(
        id = this.id,
        name = this.rouletteName
    )
}

fun RouletteRoomModel.toRouletteModel(options: List<RouletteOption>): RouletteModel {
    return RouletteModel(
        id = this.id,
        rouletteName = this.name,
        options = options
    )
}

fun RouletteOption.toRoomOptionModel(): RouletteOptionRoomModel {
    return RouletteOptionRoomModel(
        id = this.id,
        nameOption = this.rouletteOption,
        idRoulette = this.rouletteId
    )
}

fun RouletteOptionRoomModel.toOptionModel(): RouletteOption {
    return RouletteOption(
        id = this.id,
        rouletteOption = this.nameOption,
        rouletteId = this.idRoulette
    )
}
