package im.syf.busschedule.database.schedule

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule ORDER BY arrival_time ASC")
    suspend fun getAll(): List<Schedule>

    @Query("SELECT * FROM schedule WHERE stop_name = :stopName ORDER BY arrival_time ASC")
    suspend fun getByStopName(stopName: String): List<Schedule>
}
