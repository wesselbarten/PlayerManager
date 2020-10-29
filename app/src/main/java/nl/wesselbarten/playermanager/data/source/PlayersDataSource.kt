package nl.wesselbarten.playermanager.data.source

import nl.wesselbarten.playermanager.domain.model.Player
import nl.wesselbarten.playermanager.data.Result

interface PlayersDataSource {

    suspend fun getAll(): Result<List<Player>>
}