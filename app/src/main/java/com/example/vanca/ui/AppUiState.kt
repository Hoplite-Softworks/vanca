package com.example.vanca.ui

import com.example.vanca.model.Station

enum class AppScreen {
    Login,
    Home,
    Station,
    News,
    About,
}
data class AppUiState(
    //val currentScreen: AppScreen = AppScreen.Home,
    //val currentStation: Station? = null,
    val currentUserId: Int = 0,
)