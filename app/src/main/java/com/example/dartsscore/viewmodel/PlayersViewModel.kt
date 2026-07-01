package com.example.dartsscore.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel


data class Player(
    val name: String,
    val score: MutableState<Int> = mutableIntStateOf(0),
    val avg: MutableState<Int> = mutableIntStateOf(0),
    val hits: MutableList<Int> = mutableStateListOf()
)

class PlayersViewModel : ViewModel() {

    var gameMode = 0

    val players = mutableStateListOf<Player>()

    fun addPlayer(name: String) {
        if (name.isBlank()) return
        val player = Player(name = name)
        player.score.value = gameMode
        players.add(player)
    }

    fun deletePlayer(player: Player) {
        players.remove(player)
    }

    fun hit(idx: Int, hit: Int) {
        val player = players[idx]
        player.hits.add(hit)
        val total = player.hits.sum()
        player.score.value = gameMode - total
        player.avg.value =
            if (player.hits.isNotEmpty())
                total / player.hits.size
            else
                0
    }

    fun back(idx: Int) {
        val player = players[idx]
        if (player.hits.isEmpty()) return
        player.hits.removeAt(player.hits.lastIndex)
        val total = player.hits.sum()
        player.score.value = gameMode - total
        player.avg.value =
            if (player.hits.isNotEmpty())
                total / player.hits.size
            else
                0
    }
}