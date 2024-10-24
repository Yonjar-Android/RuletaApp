package com.example.ruletaapp.presentation.createRoulette

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ruletaapp.presentation.mainScreen.EditButton
import com.example.ruletaapp.ui.theme.blueUI
import com.example.ruletaapp.ui.theme.redUi

@Composable
fun CreateRouletteScreen(navHostController: NavHostController) {

    val tasks = listOf<String>()

    var rouletteName by rememberSaveable {
        mutableStateOf("")
    }

    var option by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = { navHostController.navigateUp() },
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow back"
                )
            }

            Text(text = "Crear Ruleta", fontSize = 40.sp, fontWeight = FontWeight.Bold)
        }

        Spacer20Dp()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            EditTextField("Nombre de la ruleta")

            Spacer20Dp()

            EditTextField("Agregar opción")

            Spacer20Dp()

            EditButton(buttonText = "Agregar", color = blueUI, height = 60) {}

            Spacer20Dp()

            EditButton(buttonText = "Crear Ruleta", color = blueUI, height = 100) {}

            Spacer20Dp()

            Text(text = "Opciones agregadas", fontSize = 24.sp, fontWeight = FontWeight.Bold)

            Spacer20Dp()

            LazyColumn {
                items(tasks) {

                }
            }
        }
    }
}

@Composable
fun EditTextField(labelText: String) {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    Text(text = labelText, fontSize = 24.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.size(5.dp))

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(10.dp),
        value = text,
        onValueChange = { text = it },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = redUi, focusedContainerColor = redUi,
            focusedTextColor = Color.White, unfocusedTextColor = Color.White,
            focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.White
        ),
        singleLine = true,
        maxLines = 1
    )
}

@Composable
fun Spacer20Dp() {
    Spacer(modifier = Modifier.size(20.dp))
}