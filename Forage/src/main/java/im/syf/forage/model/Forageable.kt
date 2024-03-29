package im.syf.forage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Forageable entity to be stored in the forageable_database.
 */
@Entity(tableName = "forageable")
data class Forageable(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val address: String,
    @ColumnInfo(name = "in_season")
    val inSeason: Boolean,
    val notes: String?,
)
