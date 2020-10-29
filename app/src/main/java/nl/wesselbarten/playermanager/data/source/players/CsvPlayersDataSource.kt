package nl.wesselbarten.playermanager.data.source.players

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nl.wesselbarten.playermanager.data.Result
import nl.wesselbarten.playermanager.data.source.PlayersDataSource
import nl.wesselbarten.playermanager.domain.model.Player
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

private const val CSV_DELIMITER = ","
private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
private const val FILE_NAME_PLAYERS = "players.csv"
private const val INDEX_ID = 0
private const val INDEX_SCORE = 1
private const val INDEX_NAME = 2
private const val INDEX_LAST_NAME = 3
private const val INDEX_CREATED = 4

class CsvPlayersDataSource(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PlayersDataSource {

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun getAll(): Result<List<Player>> {
        return withContext(ioDispatcher) {
            try {
                val inputStreamReader = InputStreamReader(context.assets.open(FILE_NAME_PLAYERS))
                val bufferedReader = BufferedReader(inputStreamReader)
                bufferedReader.readLine()

                val players = ArrayList<Player>()
                bufferedReader.forEachLine {
                    val array = it.split(CSV_DELIMITER)
                    players.add(
                        Player(
                            id = array[INDEX_ID],
                            name = array[INDEX_NAME],
                            lastName = array[INDEX_LAST_NAME],
                            score = array[INDEX_SCORE].toInt(),
                            createdAt = SimpleDateFormat(
                                DATE_FORMAT,
                                Locale.getDefault()
                            ).parse(array[INDEX_CREATED])!!
                        )
                    )
                }

                Result.Success(players)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}