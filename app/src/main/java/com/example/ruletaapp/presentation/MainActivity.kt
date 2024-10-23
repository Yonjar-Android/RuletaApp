package com.example.ruletaapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ruletaapp.presentation.createRoulette.CreateRouletteScreen
import com.example.ruletaapp.presentation.mainScreen.MainScreen
import com.example.ruletaapp.ui.theme.RuletaAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RuletaAppTheme {

                val navHostController = rememberNavController()

                NavHost(navController = navHostController, startDestination = "MainScreen") {
                    composable("MainScreen") {
                        MainScreen(navHostController)
                    }

                    composable("CreateRoulette") {
                        CreateRouletteScreen(navHostController)
                    }
                }
            }
        }
    }
}
