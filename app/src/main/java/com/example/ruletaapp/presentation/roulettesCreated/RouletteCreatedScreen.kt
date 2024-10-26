package com.example.ruletaapp.presentation.roulettesCreated

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ruletaapp.R
import com.example.ruletaapp.presentation.createRoulette.Spacer20Dp
import com.example.ruletaapp.presentation.models.RouletteModel
import com.example.ruletaapp.ui.theme.redUi

@Composable
fun RouletteCreatedScreen(
    navHostController: NavHostController,
    roulettesCreatedViewModel: RoulettesCreatedViewModel
) {

    val roulettes = roulettesCreatedViewModel.roulettesState.collectAsState()

    val message = roulettesCreatedViewModel.message.collectAsState()

    val loading = roulettesCreatedViewModel.isLoading.collectAsState()

    val context = LocalContext.current

    var show by rememberSaveable {
        mutableStateOf(false)
    }

    var rouletteSelected by rememberSaveable {
        mutableIntStateOf(-1)
    }

    if (loading.value) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = redUi)
        }
    }

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
            itemsIndexed(roulettes.value) { index, roulette ->
                RouletteItem(roulette.rouletteName, roulette.id, navHostController) {
                    show = true
                    rouletteSelected = index
                }

            }
        }
    }

    if (show) {
        DeleteDialog(
            roulette = roulettes.value[rouletteSelected],
            viewModel = roulettesCreatedViewModel
        ) {
            show = false
        }
    }

    // Muestra el mensaje actualizado desde el viewModel y reinicia el estado
    if (message.value.isNotBlank()) {
        Toast.makeText(context, message.value, Toast.LENGTH_SHORT).show()
        roulettesCreatedViewModel.restartState()
    }

}

@Composable
fun RouletteItem(
    title: String,
    rouletteId: Int,
    navHostController: NavHostController,
    openDialog: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(redUi)
            .clickable {
                navHostController.navigate("DetailRoulette/${title}/${rouletteId}")
            },
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

        Icon(imageVector = Icons.Default.Clear, contentDescription = "Delete Icon",
            modifier = Modifier
                .size(40.dp)
                .padding(5.dp)
                .align(Alignment.Top)
                .clickable {
                    openDialog.invoke()
                })
    }
    Spacer20Dp()
}

@Composable
fun DeleteDialog(
    roulette: RouletteModel,
    viewModel: RoulettesCreatedViewModel,
    closeDialog: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {

        },
        confirmButton = {
            Button(onClick = {
                viewModel.deleteRoulette(roulette)
                closeDialog.invoke()
            }) {
                Text(text = "Eliminar", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }, dismissButton = {
            Button(onClick = {
                closeDialog.invoke()
            }) {
                Text(text = "Cancelar", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        },
        title = {
            Text(
                text = "¿Deseas crear la ruleta llamada ${roulette.rouletteName}?",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        })
}