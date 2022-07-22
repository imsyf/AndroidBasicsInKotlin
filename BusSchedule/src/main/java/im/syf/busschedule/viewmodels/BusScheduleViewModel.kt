package im.syf.busschedule.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import im.syf.busschedule.database.schedule.Schedule
import im.syf.busschedule.database.schedule.ScheduleDao
import kotlinx.coroutines.withContext

class BusScheduleViewModel(
    private val scheduleDao: ScheduleDao,
) : ViewModel() {

    suspend fun fullSchedule(): List<Schedule> =
        withContext(viewModelScope.coroutineContext) {
            scheduleDao.getAll()
        }

    suspend fun scheduleForStopName(name: String): List<Schedule> =
        withContext(viewModelScope.coroutineContext) {
            scheduleDao.getByStopName(name)
        }
}
