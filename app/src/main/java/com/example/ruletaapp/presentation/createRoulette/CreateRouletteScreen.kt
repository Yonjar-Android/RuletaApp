package com.example.ruletaapp.presentation.createRoulette

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.ruletaapp.presentation.mainScreen.EditButton
import com.example.ruletaapp.presentation.models.RouletteModel
import com.example.ruletaapp.presentation.models.RouletteOption
import com.example.ruletaapp.ui.theme.blueUI
import com.example.ruletaapp.ui.theme.redUi

@Composable
fun CreateRouletteScreen(
    navHostController: NavHostController,
    createRouletteViewModel: CreateRouletteViewModel
) {

    var options by rememberSaveable { mutableStateOf(listOf<RouletteOption>()) }

    val message = createRouletteViewModel.message.collectAsState()

    val loading = createRouletteViewModel.isLoading.collectAsState()

    val context = LocalContext.current

    var show by rememberSaveable {
        mutableStateOf(false)
    }

    var rouletteName by rememberSaveable {
        mutableStateOf("")
    }

    var option by rememberSaveable {
        mutableStateOf("")
    }

    if (loading.value){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator(color = redUi)
        }
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
            EditTextField("Nombre de la ruleta", rouletteName) { rouletteName = it }

            Spacer20Dp()

            EditTextField("Agregar opción", option) { option = it }

            Spacer20Dp()

            EditButton(buttonText = "Agregar", color = blueUI, height = 60) {
                if (option.isBlank()) {
                    Toast.makeText(
                        context, "No puedes crear una opción vacía",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    options = options + RouletteOption(rouletteOption = option)
                    option = ""
                }
            }

            Spacer20Dp()

            EditButton(buttonText = "Crear Ruleta", color = blueUI, height = 60) {
                if (rouletteName.isBlank()) {
                    Toast.makeText(
                        context, "No puedes crear una ruleta con un nombre vacío",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    show = true
                }
            }

            Spacer20Dp()

            Text(text = "Opciones agregadas", fontSize = 24.sp, fontWeight = FontWeight.Bold)

            Spacer20Dp()

            LazyColumn {
                items(options) {
                    OptionItem(it) { options = options - it }
                }
            }
        }
    }

    // Muestra el diálogo para confirmar la creacion de una ruleta
    if (show) {
        ConfirmDialog(rouletteName = rouletteName,
            viewModel = createRouletteViewModel,
            rouletteOptions = options, cleanFields = {
                option = ""
                rouletteName = ""
                options = listOf()
            })
        { show = false }
    }

    // Muestra el mensaje actualizado desde el viewModel y reinicia el estado
    if (message.value.isNotBlank()){
        Toast.makeText(context, message.value, Toast.LENGTH_SHORT).show()
        createRouletteViewModel.restartState()
    }

}

@Composable
fun OptionItem(rouletteOption: RouletteOption, deleteOption: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(redUi),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = rouletteOption.rouletteOption,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .weight(4f),
            textAlign = TextAlign.Center,
        )

        IconButton(onClick = {
            deleteOption.invoke()
        }, modifier = Modifier.align(Alignment.CenterVertically)) {
            Icon(
                painter = painterResource(id = R.drawable.trash_icon),
                contentDescription = "trash icon to delete",
                modifier = Modifier.size(20.dp),
                tint = Color(0XFFDDDDDD)
            )
        }
    }

    Spacer20Dp()
}

@Composable
fun EditTextField(labelText: String, value: String, onValueText: (String) -> Unit) {

    Text(text = labelText, fontSize = 24.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.size(5.dp))

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(10.dp),
        value = value,
        onValueChange = {
            onValueText(it)
        },
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
fun ConfirmDialog(
    rouletteName: String,
    viewModel: CreateRouletteViewModel,
    rouletteOptions: List<RouletteOption>,
    cleanFields: () -> Unit,
    closeDialog: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {

        },
        confirmButton = {
            Button(onClick = {
                viewModel.createRoulette(
                    RouletteModel(rouletteName = rouletteName, options = rouletteOptions)
                )
                cleanFields.invoke()
                closeDialog.invoke()
            }) {
                Text(text = "Crear", fontSize = 18.sp, fontWeight = FontWeight.Bold)
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
                text = "¿Deseas crear la ruleta llamada ${rouletteName}?",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        })
}

@Composable
fun Spacer20Dp() {
    Spacer(modifier = Modifier.size(20.dp))
}