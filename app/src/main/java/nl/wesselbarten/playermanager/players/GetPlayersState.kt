package nl.wesselbarten.playermanager.players

import nl.wesselbarten.playermanager.domain.model.Player

sealed class GetPlayersState {

    class Success(val players: List<Player>) : GetPlayersState()

    object Error : GetPlayersState()
}