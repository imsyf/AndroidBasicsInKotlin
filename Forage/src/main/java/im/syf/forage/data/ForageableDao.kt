package im.syf.forage.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import im.syf.forage.model.Forageable
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for database interaction.
 */
@Dao
interface ForageableDao {

    @Query("SELECT * FROM forageable")
    fun getForageables(): Flow<List<Forageable>>

    @Query("SELECT * FROM forageable WHERE id = :id")
    fun getForageable(id: Long): Flow<Forageable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(forageable: Forageable)

    @Update
    suspend fun update(forageable: Forageable)

    @Delete
    suspend fun delete(forageable: Forageable)
}
