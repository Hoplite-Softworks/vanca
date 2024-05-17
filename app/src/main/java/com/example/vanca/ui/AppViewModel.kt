package com.example.vanca.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.vanca.data.Datasource
import com.example.vanca.model.News
import com.example.vanca.model.Station
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel: ViewModel() {
    // App UI State
    private val _appUiState = MutableStateFlow(AppUiState())
    val appUiState: StateFlow<AppUiState> = _appUiState.asStateFlow()

    private val datasource = Datasource()

    var username by mutableStateOf("")
        private set

    fun updateUsernameInput(newUsernameInput: String) {
        username = newUsernameInput
    }

    fun checkCredentials(): Boolean {
        val users = datasource.loadAllUsers()

        users.forEach { user ->
            Log.d("AppViewModel", "Maybe $username is ${user.username}")
            if(user.username == username.trim()) {
                updateCurrentUserId(user.id)
                return true
            }
        }

        return false
    }

    private fun updateCurrentUserId(userId: Int) {
        _appUiState.value = AppUiState(currentUserId = userId)
    }

    fun loadAllStations(): List<Station> {
        return datasource.loadAllStations()
    }

    fun loadBookmarkedStations(userId: Int): List<Station> {
        return datasource.loadBookmarkedStations(userId)
    }

    fun loadRecentStations(userId: Int): List<Station> {
        return datasource.loadRecentStations(userId)
    }

    fun loadNews(): List<News> {
        return datasource.loadNews()
    }

    fun resetCredentials() {
        username = ""
    }

}