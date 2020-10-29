package nl.wesselbarten.playermanager.players

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import nl.wesselbarten.playermanager.data.Result
import nl.wesselbarten.playermanager.domain.model.PlayerSortOrder
import nl.wesselbarten.playermanager.domain.repository.PlayerRepository

private val DEFAULT_PLAYER_SORT_ORDER = PlayerSortOrder.NAME

class PlayersViewModel @ViewModelInject constructor(
    private val playerRepository: PlayerRepository
) : ViewModel() {

    private val _selectedPlayerSortOrder = MutableLiveData(DEFAULT_PLAYER_SORT_ORDER)

    private val _getPlayersState = MutableLiveData<GetPlayersState>()
    val getPlayersState: LiveData<GetPlayersState> = _getPlayersState.switchMap { state ->
        _selectedPlayerSortOrder.map { sortOrder ->
            getSortedPlayers(state, sortOrder)
        }
    }

    private val _playerSortOrders = MutableLiveData<List<PlayerSortOrder>>()
    val playerSortOrders: LiveData<List<PlayerSortOrder>> =
        liveData {
            val result = playerRepository.getSortOrders()
            if (result is Result.Success) {
                this.emit(result.data)
            }
        }.switchMap { sortOrders ->
            _selectedPlayerSortOrder.map { currentOrder ->
                sortOrders.onEach { it.isActive = it == currentOrder }.toList()
            }
        }

    init {
        viewModelScope.launch {
            _getPlayersState.value = when (val result = playerRepository.getAll()) {
                is Result.Success -> GetPlayersState.Success(result.data)
                is Result.Error -> GetPlayersState.Error
            }
        }

//        viewModelScope.launch {
//            val result = playerRepository.getSortOrders()
//            if (result is Result.Success) {
//                _playerSortOrders.value = result.data
//            }
//        }
    }

    fun sortPlayers(playerSortOrder: PlayerSortOrder) {
        _selectedPlayerSortOrder.value = playerSortOrder
    }

    private fun getSortedPlayers(
        getPlayersState: GetPlayersState,
        playerSortOrder: PlayerSortOrder
    ): GetPlayersState {
        return if (getPlayersState is GetPlayersState.Success) {
            val players = when (playerSortOrder) {
                PlayerSortOrder.NAME -> {
                    getPlayersState.players.sortedBy { it.printName() }
                }
                PlayerSortOrder.CREATED -> {
                    getPlayersState.players.sortedByDescending { it.createdAt.time }
                }
                PlayerSortOrder.SCORE -> {
                    getPlayersState.players.sortedByDescending { it.score }
                }
            }
            GetPlayersState.Success(players)
        } else {
            getPlayersState
        }
    }
}