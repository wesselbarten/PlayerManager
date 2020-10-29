package nl.wesselbarten.playermanager.domain.model

import android.content.Context
import nl.wesselbarten.playermanager.R
import java.text.DateFormat
import java.util.Date

data class Player(
    val id: String,
    val name: String,
    val lastName: String,
    val score: Int,
    val createdAt: Date
) {

    fun printName(): String = "$name $lastName"

    fun printScore(context: Context): String = String.format(context.getString(R.string.score), score)

    fun printDate(): String {
        return DateFormat.getDateTimeInstance().format(createdAt)
    }
}