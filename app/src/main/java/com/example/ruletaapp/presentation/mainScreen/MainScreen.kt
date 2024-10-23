package com.example.ruletaapp.presentation.mainScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ruletaapp.ui.theme.redUi

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Ruletas", fontSize = 40.sp, fontWeight = FontWeight.Bold)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            EditButton("Crear Ruleta", redUi)
            Spacer(modifier = Modifier.size(20.dp))
            EditButton("Ruletas Creadas", redUi)
        }
    }
}

@Composable
fun EditButton(buttonText: String, color: Color) {
    Button(
        onClick = {

        },
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = Modifier
            .width(250.dp)
            .height(100.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = buttonText,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = 36.sp
        )
    }
}