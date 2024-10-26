package com.example.ruletaapp.presentation.roulettesCreated.detailRoulette

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ruletaapp.presentation.createRoulette.Spacer20Dp
import com.example.ruletaapp.presentation.mainScreen.EditButton
import com.example.ruletaapp.presentation.models.RouletteOption
import com.example.ruletaapp.ui.theme.blueUI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun DetailRouletteScreen(
    navHostController: NavHostController,
    detailRouletteViewModel: DetailRouletteViewModel,
    rouletteName: String,
    rouletteId: Int
) {

    LaunchedEffect(Unit) {
        detailRouletteViewModel.getRouletteById(rouletteId)
    }

    val rouletteOptions = detailRouletteViewModel.roulette.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = { navHostController.navigateUp() },
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow back"
                )
            }
            Text(text = rouletteName, fontSize = 40.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.size(40.dp))

        Spacer20Dp()

        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            RouletteFortune(rouletteOptions.value.options)

            Spacer(modifier = Modifier.height(40.dp))
            
            EditButton(buttonText = "Girar", color = blueUI, height = 60) {

            }
        }
    }
}

@Composable
fun RouletteFortune(options: List<RouletteOption>) {
    println(options)

    Canvas(modifier = Modifier.size(400.dp)) {

        // Define el número de secciones
        val sectionCount = options.size
        val sweepAngle = 360f / sectionCount // Ángulo para cada sección
        var startAngle = 0f // Ángulo inicial

        // Dibuja cada sección
        for (i in options.indices) {
            // Genera un color diferente para cada sección
            val colorSection = Color(
                red = ((i * (123..200).random()) % 256).toFloat() / 255f,
                green = ((i * (67..200).random()) % 256).toFloat() / 255f,
                blue = ((i * 89) % 256).toFloat() / 255f
            )

            // Dibuja el arco correspondiente a la opción
            drawArc(
                color = colorSection,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = true // Usar el centro para hacer un círculo
            )

            // Calcular la posición del texto
            val radius = size.minDimension / 2 * 0.6f // Ajusta el radio para el texto
            val angle = Math.toRadians((startAngle + sweepAngle / 2).toDouble()) // Ángulo en radianes
            val x = (radius * cos(angle)).toFloat() + center.x // Posición X
            val y = (radius * sin(angle)).toFloat() + center.y // Posición Y

            // Dibuja el texto en la posición calculada
            drawContext.canvas.nativeCanvas.drawText(
                options[i].rouletteOption, // El texto a dibujar
                x,
                y,
                Paint().apply {
                    color = android.graphics.Color.BLACK // Color del texto
                    textAlign = Paint.Align.CENTER // Alinear el texto al centro
                    textSize = 30f // Tamaño del texto
                }
            )

            startAngle += sweepAngle // Actualiza el ángulo inicial para la siguiente sección
        }
    }
}









