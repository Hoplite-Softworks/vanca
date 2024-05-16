package com.example.vanca

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vanca.ui.About
import com.example.vanca.ui.Home
import com.example.vanca.ui.Station

@Composable
fun VancaApp(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            Home(modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background_color)),
                onStationClicked = { navController.navigate("station/$it") },
                onTeamLinkClicked = { navController.navigate("about") }
            )
        }

        composable(
            "station/{id}",
            arguments = listOf(navArgument("id") {type = NavType.IntType})
        ) {
            Station(
                stationId = it.arguments?.getInt("id") ?: 0,
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.background_color)),
                onTeamLinkClicked = { navController.navigate("about") }
            )
        }

        composable("about") {
            About()
        }
    }
}