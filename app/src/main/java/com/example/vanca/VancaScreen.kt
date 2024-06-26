package com.example.vanca

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vanca.ui.About
import com.example.vanca.ui.AppScreen
import com.example.vanca.ui.AppViewModel
import com.example.vanca.ui.Home
import com.example.vanca.ui.LoginScreen
import com.example.vanca.ui.News
import com.example.vanca.ui.Search
import com.example.vanca.ui.Station

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun VancaApp() {
    val navController = rememberNavController()
    val viewModel: AppViewModel = viewModel()
    NavHost(navController = navController, startDestination = "login") {

        composable("splash") {

        }

        composable("login") {
            LoginScreen(
                viewModel = viewModel,
                onSuccessfulLogin = {
                    navController.navigate("home") {
                        popUpTo("login", popUpToBuilder = { inclusive = true })
                    }
                },
                onTeamLinkClicked = { navController.navigate("about") }
            )
        }

        composable("home") {
            Home(
                viewModel = viewModel,
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.background_color)),
                onStationClicked = { navController.navigate("station/$it") },
                onTeamLinkClicked = { navController.navigate("about") },
                onNewsClicked = { navController.navigate("news/$it") },
                onSearched = {navController.navigate("search/$it")}
            )
        }

        composable("search/{query}") { entry ->
            Search(
                viewModel = viewModel,
                stationQuery = entry.arguments?.getString("query") ?: "",
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.background_color)),
                onTeamLinkClicked = { navController.navigate("about") },
                onStationClicked = { navController.navigate("station/$it") },
                onSearched = {navController.navigate("search/$it")}
            )
        }

        composable(
            "station/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            Station(
                viewModel = viewModel,
                stationId = it.arguments?.getInt("id") ?: 0,
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.background_color)),
                onTeamLinkClicked = { navController.navigate("about") },
                onNoStationFound = { navController.navigate("home") }
            )
        }

        composable(
            "news/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            News(
                viewModel = viewModel,
                newsId = it.arguments?.getInt("id") ?: 0,
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.background_color)),
                onTeamLinkClicked = { navController.navigate("about") }
            )
        }

        composable("about") {
            About(viewModel = viewModel, modifier = Modifier.fillMaxSize()
                .background(colorResource(id = R.color.background_color)),)
        }
    }
}

