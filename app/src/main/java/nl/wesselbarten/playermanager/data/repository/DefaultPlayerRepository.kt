package nl.wesselbarten.playermanager.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nl.wesselbarten.playermanager.data.Result
import nl.wesselbarten.playermanager.data.source.PlayersDataSource
import nl.wesselbarten.playermanager.domain.model.Player
import nl.wesselbarten.playermanager.domain.model.PlayerSortOrder
import nl.wesselbarten.playermanager.domain.repository.PlayerRepository

class DefaultPlayerRepository(
    private val playersDataSource: PlayersDataSource,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : PlayerRepository {

    override suspend fun getAll(): Result<List<Player>> {
        return withContext(defaultDispatcher) {
            playersDataSource.getAll()
        }
    }

    override suspend fun getSortOrders(): Result<List<PlayerSortOrder>> {
        return Result.Success(PlayerSortOrder.values().toList())
    }
}