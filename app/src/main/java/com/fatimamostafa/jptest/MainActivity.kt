package com.fatimamostafa.jptest

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.fatimamostafa.jptest.compose.Screen
import com.fatimamostafa.jptest.presentation.PlanetScreen
import com.fatimamostafa.jptest.presentation.PlanetViewModel
import com.fatimamostafa.jptest.presentation.planetdetail.PlanetDetailScreen
import com.fatimamostafa.jptest.presentation.planetdetail.PlanetDetailViewModel
import com.fatimamostafa.jptest.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    MainAppNavHost(
        navController = navController,
    )
}

@Composable
fun MainAppNavHost(navController: NavHostController) {
    val activity = (LocalContext.current as Activity)
    NavHost(navController = navController, startDestination = Screen.PlanetScreen.route) {
        composable(route = Screen.PlanetScreen.route) {
            val viewModel = hiltViewModel<PlanetViewModel>()
            val planets = viewModel.planetPagingFlow.collectAsLazyPagingItems()
            PlanetScreen(
                planets = planets,
                onPlanetClick = {
                    navController.navigate(
                        Screen.PlanetDetail.createRoute(
                            planetName = it.name,
                        ),
                    )
                },
            )
        }
        composable(
            route = Screen.PlanetDetail.route,
            arguments = Screen.PlanetDetail.navArguments,
        ) {
            val viewModel = hiltViewModel<PlanetDetailViewModel>()
            val planetName = viewModel.planetName
            PlanetDetailScreen(
                navController = navController,
                planetName = planetName
            )
        }
    }
}