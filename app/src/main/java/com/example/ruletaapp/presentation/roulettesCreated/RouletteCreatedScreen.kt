package com.example.ruletaapp.presentation.roulettesCreated

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ruletaapp.R
import com.example.ruletaapp.presentation.createRoulette.Spacer20Dp
import com.example.ruletaapp.ui.theme.redUi

@Composable
fun RouletteCreatedScreen(navHostController: NavHostController) {

    val list = listOf<String>("Ruleta de papas", "Ruleta de animales", "Ruleta de comidas")


    Column(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.systemBars)
            .fillMaxSize(),
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
            Text(text = "Ruletas Creadas", fontSize = 40.sp, fontWeight = FontWeight.Bold)
        }

        Spacer20Dp()

        LazyColumn {
            items(list) {
                RouletteItem(it)
            }
        }
    }
}

@Composable
fun RouletteItem(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(redUi),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.roulette),
            contentDescription = "Roulette Image",
            modifier = Modifier
                .weight(1f)
                .size(125.dp)
                .padding(5.dp)
        )

        Text(
            text = title,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1.5f)
                .padding(10.dp),
            color = Color.White,
            lineHeight = 36.sp
        )
    }
    Spacer20Dp()
}