package com.example.dartsscore.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class PlayersViewModel : ViewModel() {
    var gameMode = 0
    val playerNames = mutableStateListOf<String>()

    fun addPlayer(name: String) {
        if (name.isNotBlank()) {
            playerNames.add(name)
        }
    }

    fun deletePlayer(name: String) {
        playerNames.remove(name)
    }
}