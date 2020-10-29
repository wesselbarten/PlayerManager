package nl.wesselbarten.playermanager.domain.repository

import nl.wesselbarten.playermanager.domain.model.Player
import nl.wesselbarten.playermanager.data.Result
import nl.wesselbarten.playermanager.domain.model.PlayerSortOrder

interface PlayerRepository {

    suspend fun getAll(): Result<List<Player>>

    suspend fun getSortOrders(): Result<List<PlayerSortOrder>>
}