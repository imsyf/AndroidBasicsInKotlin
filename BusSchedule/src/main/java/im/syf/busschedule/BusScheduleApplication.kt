package im.syf.busschedule

import android.app.Application
import im.syf.busschedule.database.AppDatabase

class BusScheduleApplication : Application() {

    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}
