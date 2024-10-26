package com.example.ruletaapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ruletaapp.presentation.createRoulette.CreateRouletteScreen
import com.example.ruletaapp.presentation.createRoulette.CreateRouletteViewModel
import com.example.ruletaapp.presentation.mainScreen.MainScreen
import com.example.ruletaapp.presentation.roulettesCreated.RouletteCreatedScreen
import com.example.ruletaapp.presentation.roulettesCreated.RoulettesCreatedViewModel
import com.example.ruletaapp.presentation.roulettesCreated.detailRoulette.DetailRouletteScreen
import com.example.ruletaapp.presentation.roulettesCreated.detailRoulette.DetailRouletteViewModel
import com.example.ruletaapp.ui.theme.RuletaAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RuletaAppTheme {

                val createRouletteViewModel: CreateRouletteViewModel by viewModels()

                val roulettesCreatedViewModel: RoulettesCreatedViewModel by viewModels()

                val detailRouletteViewModel: DetailRouletteViewModel by viewModels()

                val navHostController = rememberNavController()

                NavHost(navController = navHostController, startDestination = "MainScreen") {
                    composable("MainScreen") {
                        MainScreen(navHostController)
                    }

                    composable("CreateRoulette") {
                        CreateRouletteScreen(
                            navHostController,
                            createRouletteViewModel = createRouletteViewModel
                        )
                    }
                    composable("RoulettesCreated") {
                        RouletteCreatedScreen(
                            navHostController,
                            roulettesCreatedViewModel = roulettesCreatedViewModel
                        )
                    }

                    composable(
                        route = "DetailRoulette/{rouletteName}/{rouletteId}",
                        arguments = listOf(
                            navArgument("rouletteName") { type = NavType.StringType },
                            navArgument("rouletteId") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        val rouletteName = backStackEntry.arguments?.getString("rouletteName")
                        val rouletteId = backStackEntry.arguments?.getInt("rouletteId")

                        DetailRouletteScreen(
                            navHostController = navHostController,
                            detailRouletteViewModel = detailRouletteViewModel,
                            rouletteName = rouletteName.orEmpty(),
                            rouletteId = rouletteId ?: 0
                        )
                    }
                }
            }
        }
    }
}
