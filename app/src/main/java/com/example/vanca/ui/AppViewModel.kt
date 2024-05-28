package com.example.vanca.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.vanca.data.Datasource
import com.example.vanca.model.News
import com.example.vanca.model.Station
import com.example.vanca.model.TeamMember
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel : ViewModel() {
    // App UI State
    private val _appUiState = MutableStateFlow(AppUiState())
    val appUiState: StateFlow<AppUiState> = _appUiState.asStateFlow()

    private val datasource = Datasource()

    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun updateUsernameInput(newUsernameInput: String) {
        username = newUsernameInput
    }

    fun updatePasswordInput(newPasswordInput: String) {
        password = newPasswordInput
    }

    fun checkCredentials(onLoginSuccess: () -> Unit) {
        val users = datasource.loadAllUsers()

        users.forEach { user ->
            //Log.d("AppViewModel", "Maybe $username is ${user.username}")
            if (user.username == username.trim() && user.password == password.trim()) {
                updateCurrentUserId(user.id)
                onLoginSuccess()
            }

        }
        resetCredentials()
    }

    private fun updateCurrentUserId(userId: Int) {
        _appUiState.value = AppUiState(currentUserId = userId)
    }

    private fun resetCredentials() {
        username = ""
    }

    /* ============================================================ */
    var stationSearched by mutableStateOf("")
        private set

    fun updateStationInput(newStationInput: String) {
        stationSearched = newStationInput
    }

    fun searchStationInitialized(onStationSearch: (String) -> Unit) {
        if (stationSearched != "") {
            onStationSearch(stationSearched)
        }
    }

    fun searchStations(query: String): List<Station> {
        val allStations = datasource.loadAllStations()
        val results = mutableListOf<Station>()
        val maxDistance = 3

        for (station in allStations) {
            val distance = levenshteinDistance(query.lowercase(), station.stationName.lowercase())
            if (distance <= maxDistance) {
                results.add(station)
            }
        }
        return results
    }

    fun resetStationInput() {
        stationSearched = ""
    }

    private fun loadAllStations(): List<Station> {
        return datasource.loadAllStations()
    }

    fun findStationWithId(stationId: Int): Station? {
        return datasource.findStationWithId(stationId)
    }

    fun loadBookmarkedStations(userId: Int): List<Station> {
        return datasource.loadBookmarkedStations(userId)
    }

    fun loadRecentStations(userId: Int): List<Station> {
        return datasource.loadRecentStations(userId)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadNews(): List<News> {
        return datasource.loadNews()
    }

    fun loadTeamMembers(): List<TeamMember> {
        return datasource.loadTeamMembers()
    }
}