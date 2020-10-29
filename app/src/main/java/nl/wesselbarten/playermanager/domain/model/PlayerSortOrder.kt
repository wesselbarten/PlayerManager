package nl.wesselbarten.playermanager.domain.model

import java.util.*

enum class PlayerSortOrder {
    NAME,
    CREATED,
    SCORE;

    var isActive: Boolean = false

    fun printName(): String {
        return name.toLowerCase(Locale.getDefault()).capitalize(Locale.getDefault())
    }
}