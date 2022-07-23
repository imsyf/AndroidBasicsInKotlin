package im.syf.busschedule.viewmodels

import androidx.lifecycle.ViewModel
import im.syf.busschedule.database.schedule.Schedule
import im.syf.busschedule.database.schedule.ScheduleDao
import kotlinx.coroutines.flow.Flow

class BusScheduleViewModel(
    private val scheduleDao: ScheduleDao,
) : ViewModel() {

    fun fullSchedule(): Flow<List<Schedule>> =
        scheduleDao.getAll()

    fun scheduleForStopName(name: String): Flow<List<Schedule>> =
        scheduleDao.getByStopName(name)
}
