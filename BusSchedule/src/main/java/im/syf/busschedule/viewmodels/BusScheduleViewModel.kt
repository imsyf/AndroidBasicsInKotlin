package im.syf.busschedule.viewmodels

import androidx.lifecycle.ViewModel
import im.syf.busschedule.database.schedule.Schedule
import im.syf.busschedule.database.schedule.ScheduleDao

class BusScheduleViewModel(
    private val scheduleDao: ScheduleDao,
) : ViewModel() {

    fun fullSchedule(): List<Schedule> = scheduleDao.getAll()

    fun scheduleForStopName(name: String): List<Schedule> = scheduleDao.getByStopName(name)
}
